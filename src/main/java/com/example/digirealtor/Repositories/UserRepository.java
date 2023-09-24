package com.example.digirealtor.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.digirealtor.Models.UserModel;


public interface UserRepository  extends MongoRepository<UserModel,String>{

    Optional<UserModel> findByEmail(String email);
}
