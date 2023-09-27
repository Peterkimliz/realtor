package com.example.digirealtor.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.digirealtor.Models.Category;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    Optional<Category> findByName(String id);

    List<Category> findByType(String type);
    
}
