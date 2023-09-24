package com.example.digirealtor.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.SigninRequests;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class AuthService {
    @Autowired 
    UserRepository userRepository;

    public UserModel createUser(SigninRequests signinRequests){
        Optional<UserModel> foundUser=userRepository.findByEmail(signinRequests.getEmail());
        if(foundUser.isPresent()){
            throw
        }
    }
    
}
