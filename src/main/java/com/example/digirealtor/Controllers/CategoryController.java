package com.example.digirealtor.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.CategoryDto;
import com.example.digirealtor.Services.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") String id) {
        return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(id), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> getCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") String id) {
        return new ResponseEntity<CategoryDto>(categoryService.updateCategoryById(categoryDto,id), HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<String>("Category Deleted", HttpStatus.OK);

    }
 

    @GetMapping("/all/{type}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByType(@PathVariable("type") String type) {
        return new ResponseEntity<List<CategoryDto>>(categoryService.getCategoriesByType(type), HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategies() {
        return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.CREATED);

    }

}
