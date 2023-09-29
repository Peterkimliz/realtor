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

import com.example.digirealtor.Dtos.ProductDto;
import com.example.digirealtor.Services.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated ProductDto productDto,
            @PathVariable("userId") String userId) {
        return new ResponseEntity<ProductDto>(productService.createProduct(productDto, userId), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") String id) {
        return new ResponseEntity<ProductDto>(productService.getProductById(id), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> getCategory(@RequestBody ProductDto productDto, @PathVariable("id") String id) {
        return new ResponseEntity<ProductDto>(productService.updateProductById(productDto, id), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllCategory(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) int bedrooms,
            @RequestParam(required = false) int bathrooms,
            @RequestParam(required = false) int kitchens,
            @RequestParam(required = false) int startPrice,
            @RequestParam(required = false) int endPrice
            ) {
        return new ResponseEntity<List<ProductDto>>(productService.getProductByFilters(type,category,bedrooms,bathrooms,kitchens,startPrice,endPrice), HttpStatus.CREATED);

    }

}
