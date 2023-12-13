package com.example.digirealtor.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.digirealtor.Models.Property;

@Repository
public interface PropertyRepository extends MongoRepository<Property,String>{

    
}
