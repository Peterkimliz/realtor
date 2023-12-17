package com.example.digirealtor.Services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.PropertyCategoryRequestDto;
import com.example.digirealtor.Dtos.PropertyCategoryResponse;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Models.PropertyCategory;
import com.example.digirealtor.Repositories.PropertyCategoryRepository;

@Service
public class PropertyCategoryService {

    @Autowired
    private PropertyCategoryRepository propertyCategoryRepository;

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



}
