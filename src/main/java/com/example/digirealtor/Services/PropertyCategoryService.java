package com.example.digirealtor.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.CategoryDto;
import com.example.digirealtor.Dtos.PropertyCategoryRequestDto;
import com.example.digirealtor.Dtos.PropertyCategoryResponse;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Category;
import com.example.digirealtor.Models.PropertyCategory;
import com.example.digirealtor.Repositories.CategoryRepository;
import com.example.digirealtor.Repositories.PropertyCategoryRepository;

@Service
public class PropertyCategoryService {

    @Autowired
    private PropertyCategoryRepository propertyCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public PropertyCategoryResponse createPropertyCategory(PropertyCategoryRequestDto propertyCategoryDto) {
        Optional<PropertyCategory> prOptional = propertyCategoryRepository
                .findByName(propertyCategoryDto.getName().toLowerCase());
        if (prOptional.isPresent()) {
            throw new FoundException("Property category Already Exists");
        }
        PropertyCategory propertyCategory = PropertyCategory.builder().createdAt(new Date(System.currentTimeMillis()))
                .name(propertyCategoryDto.getName().toLowerCase()).build();

        propertyCategoryRepository.save(propertyCategory);

        return PropertyCategoryResponse.builder().id(propertyCategory.getId()).name(propertyCategory.getName())
                .createdAt(propertyCategory.getCreatedAt()).build();
    }

    public List<PropertyCategoryResponse> getAllPropertyCategories() {

        List<PropertyCategory> propertyCategories = propertyCategoryRepository.findAll();
        if (propertyCategories.size() > 0) {
            return propertyCategories.stream()
                    .map(e -> PropertyCategoryResponse.builder()
                            .createdAt(e.getCreatedAt())
                            .id(e.getId())
                            .name(e.getName())
                            .categoryDto(e.getCategories().stream().map(j -> CategoryDto.builder()
                                    .id(j.getId())
                                    .name(j.getName())
                                    .type(j.getType())
                                    .build()).toList())
                            .build())
                    .toList();

        } else {
            return new ArrayList<>();
        }
    }

    public PropertyCategoryResponse updatePropertyCategory(String propertyId, String subcategoryId) {

        Optional<PropertyCategory> prOptional = propertyCategoryRepository.findById(propertyId);
        if (!prOptional.isPresent()) {
            throw new NotFoundException("Property with id not found");
        }
        Optional<Category> pCOptional = categoryRepository.findById(subcategoryId);
        if (!pCOptional.isPresent()) {
            throw new NotFoundException("Category with id not found");
        }
        boolean isPresent = prOptional.get().getCategories().stream().anyMatch(e -> e.getId().equals(subcategoryId));
        if (isPresent == true) {
            throw new FoundException("Sub category already exists in this property");
        }
        PropertyCategory propertyCategory = prOptional.get();
        List<Category> categories = propertyCategory.getCategories();
        categories.add(pCOptional.get());
        propertyCategory.setCategories(categories);
        propertyCategoryRepository.save(propertyCategory);
        return PropertyCategoryResponse.builder()
                .createdAt(propertyCategory.getCreatedAt())
                .id(propertyCategory.getId())
                .categoryDto(propertyCategory.getCategories().stream().map(j -> CategoryDto.builder()
                        .id(j.getId())
                        .name(j.getName())
                        .type(j.getType())
                        .build()).toList())

                .name(propertyCategory.getName())
                .build();
    }

    


}
