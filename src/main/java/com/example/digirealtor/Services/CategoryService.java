package com.example.digirealtor.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.CategoryDto;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Models.Category;
import com.example.digirealtor.Repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Optional<Category> foundCategory = categoryRepository.findByName(categoryDto.getName().toLowerCase());
        if (foundCategory.isPresent()) {
            throw new FoundException("Category with that name already exists");
        }
        Category category = Category.builder().createdAt(new Date(System.currentTimeMillis()))
          .name(categoryDto.getName().toLowerCase()).type(categoryDto.getType()).build();
        categoryRepository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    public CategoryDto getCategoryById(String id) {
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (!foundCategory.isPresent()) {
            throw new FoundException("Category not found");
        }
        Category category=foundCategory.get();
        return CategoryDto.builder().id(category.getId()).name(category.getName()).type(category.getType()).build();

    }

    public CategoryDto updateCategoryById(CategoryDto categoryDto, String id) {
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (!foundCategory.isPresent()) {
            throw new FoundException("Category not found");
        }
        Category category = foundCategory.get();
        category.setName(categoryDto.getName() == null ? category.getName() : categoryDto.getName());
        category.setType(categoryDto.getType() == null ? category.getType() : categoryDto.getType());
        categoryRepository.save(category);

        return CategoryDto.builder().id(category.getId()).name(category.getName()).type(category.getType()).build();

    }

    public void deleteCategory(String id) {
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (!foundCategory.isPresent()) {
            throw new FoundException("Category not found");
        }

        categoryRepository.deleteById(id);

    }

    public List<CategoryDto> getCategoriesByType(String type) {
    
        List<Category> categories = categoryRepository.findByType(type);
        if (categories.size() > 0) {
            return categories.stream().map(e -> CategoryDto.builder().id(e.getId()).name(e.getName()).type(e.getType()).build()).toList();

        } else {
            return new ArrayList<>();
        }

    }
    public List<CategoryDto> getAllCategories() {
    
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() > 0) {
            return categories.stream().map(e -> CategoryDto.builder().id(e.getId()).name(e.getName()).type(e.getType()).build()).toList();

        } else {
            return new ArrayList<>();
        }

    }

}
