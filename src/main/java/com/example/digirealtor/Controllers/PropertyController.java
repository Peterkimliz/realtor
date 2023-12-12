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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.PropertyDto;
import com.example.digirealtor.Dtos.PropertyRequestDto;
import com.example.digirealtor.Services.PropertyService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/property")
@Tag(name = "Property")
public class PropertyController {
    @Autowired
    private PropertyService productService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<PropertyDto> createProduct(@RequestBody @Validated PropertyRequestDto productDto,
            @PathVariable("userId") String userId) {
                System.out.println("hello");
        return new ResponseEntity<PropertyDto>(productService.createProperty(productDto, userId), HttpStatus.CREATED);

    }

    @GetMapping("/property/{id}")
    public ResponseEntity<PropertyDto> getProduct(@PathVariable("id") String id) {
        return new ResponseEntity<PropertyDto>(productService.getProductById(id), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PropertyDto> getCategory(@RequestBody PropertyRequestDto productDto, @PathVariable("id") String id) {
        return new ResponseEntity<PropertyDto>(productService.updateProductById(productDto, id), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDto>> getAllProperties(
                   @RequestParam(required = false) String landlord
            // @RequestParam(required = false) String category,
            // @RequestParam(required = false) int bedrooms,
            // @RequestParam(required = false) int bathrooms,
            // @RequestParam(required = false) int kitchens,
            // @RequestParam(required = false) int startPrice,
            // @RequestParam(required = false) int endPrice
            ) {
        
        return new ResponseEntity<List<PropertyDto>>(productService.getProductByFilters(landlord), HttpStatus.OK);

    }

}
