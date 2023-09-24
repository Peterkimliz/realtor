package com.example.digirealtor.Services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.SigninRequests;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
@Autowired
PasswordEncoder passwordEncoder;
    public UserModel createUser(SigninRequests signinRequests) {
        Optional<UserModel> foundUser = userRepository.findByEmail(signinRequests.getEmail());
        if (foundUser.isPresent()) {
            throw new FoundException("User with email address already exists");
        }
        UserModel userModel = UserModel.builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .email(signinRequests.getEmail())
                .fullName(signinRequests.getUsername())
                .phone(signinRequests.getPhone())
                .password(passwordEncoder.encode(signinRequests.getPassword())).build();

        userRepository.save(userModel);

    }

}
