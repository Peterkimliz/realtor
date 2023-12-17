package com.example.digirealtor.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@GetMapping("/all")
public ResponseEntity<List<PropertyCategoryResponse>> getPropertyCategory(){
    return new ResponseEntity<List<PropertyCategoryResponse>>(propertyCategoryService.getAllPropertyCategories(),HttpStatus.OK);

}
@PutMapping("/add/{propertyId}")
public ResponseEntity<PropertyCategoryResponse> addSubCategoryToPropertyCategory(@PathVariable("propertyId") String propertyId,@RequestParam(required = true) String subcategoryId){
    return new ResponseEntity<PropertyCategoryResponse>(propertyCategoryService.updatePropertyCategory(propertyId,subcategoryId),HttpStatus.OK);

}

    
}
