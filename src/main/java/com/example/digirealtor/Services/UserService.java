package com.example.digirealtor.Services;

import java.util.ArrayList;
import java.util.List;
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
        UserResponse userResponse = mapToUserDto(userModel);
        return userResponse;

    }

    private UserResponse mapToUserDto(UserModel userModel) {
        return UserResponse.builder()
        
                .createdAt(userModel.getCreatedAt())
                .email(userModel.getEmail())
                .emailVerified(userModel.getEmailVerified())
                .id(userModel.getId())
                .fullName(userModel.getFullName())
                .phone(userModel.getPhone())
                .build();
    }

    public List<UserResponse> allUsers(int pageNumber) {
        List<UserModel> users=userRepository.findAll();
        if(users.isEmpty()){
            return new ArrayList<>();

        }  
        return users.stream().map(e->mapToUserDto(e)).toList();
    }

}
