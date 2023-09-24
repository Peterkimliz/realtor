package com.example.digirealtor.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.UserResponse;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponse getUserById(String userId) {
        Optional<UserModel> foundUser = userRepository.findById(userId);
        if (!foundUser.isPresent()) {
            throw new NotFoundException("User with the id not found");
        }
        UserModel userModel = foundUser.get();
        UserResponse userResponse = UserResponse.builder()
                .createdAt(userModel.getCreatedAt())
                .email(userModel.getEmail())
                .emailVerified(userModel.getEmailVerified())
                .fullName(userModel.getFullName())
                .phone(userModel.getPhone())
        
                .build();
        return userResponse;

    }

}
