package com.example.digirealtor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.PropertyCategoryRequestDto;
import com.example.digirealtor.Dtos.PropertyCategoryResponse;
import com.example.digirealtor.Services.PropertyCategoryService;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/propertycategory/")
@Tag(name = "PropertyCategory")

public class PropertyCategoryController {

@Autowired
PropertyCategoryService propertyCategoryService;

@PostMapping("/create")
public ResponseEntity<PropertyCategoryResponse> createPropertyCategory(@RequestBody @Validated PropertyCategoryRequestDto propertyCategoryDto){
    return new ResponseEntity<PropertyCategoryResponse>(propertyCategoryService.createPropertyCategory(propertyCategoryDto),HttpStatus.CREATED);

}

    
}
