package com.example.digirealtor.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.digirealtor.Models.UserModel;
@Repository
public interface UserRepository  extends MongoRepository<UserModel,String>{

    Optional<UserModel> findByEmail(String email);
}
