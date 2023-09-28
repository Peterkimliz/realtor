package com.example.digirealtor.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.ProductDto;
import com.example.digirealtor.Repositories.ProductRepository;

@Service
public class Product {
    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto ) {
        return null;
    }

}
