package com.example.digirealtor.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.digirealtor.Models.PropertyCategory;

public interface PropertyCategoryRepository extends MongoRepository<PropertyCategory, String> {

    Optional<PropertyCategory> findByName(String name );

}
