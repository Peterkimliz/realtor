package com.example.digirealtor.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.digirealtor.Models.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>{
    
}
