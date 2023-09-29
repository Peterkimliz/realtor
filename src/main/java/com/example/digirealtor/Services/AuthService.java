package com.example.digirealtor.Services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.LoginRequest;
import com.example.digirealtor.Dtos.LoginResponse;
import com.example.digirealtor.Dtos.SigninRequests;
import com.example.digirealtor.Dtos.UserResponse;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.UserRepository;
import com.example.digirealtor.Security.JwtService;
import com.example.digirealtor.Security.UserDetailsImplementation;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserDetailsImplementation userDetailsImplementation;
     @Autowired
    AuthenticationManager authenticationManager;



    public LoginResponse createUser(SigninRequests signinRequests) {
        Optional<UserModel> foundUser = userRepository.findByEmail(signinRequests.getEmail());
        if (foundUser.isPresent()) {
            throw new FoundException("User with email address already exists");
        }
        UserModel userModel = UserModel.builder()
                .createdAt(new Date(System.currentTimeMillis()))
                .email(signinRequests.getEmail())
                .fullName(signinRequests.getUsername())
                .phone(signinRequests.getPhone())
                .password(passwordEncoder.encode(signinRequests.getPassword())).emailVerified(false).build();

        userRepository.save(userModel);

        LoginResponse loginResponse = buildLoginResponse(userModel);

        return loginResponse;

    }

     public LoginResponse LoginUser(LoginRequest loginRequest) {
       
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
    
            } catch (Exception e) {
                System.out.println(e);
                throw new NotFoundException("invalid email or password");
            }
            Optional<UserModel> user = userRepository.findByEmail(loginRequest.getEmail());
            LoginResponse loginResponse = buildLoginResponse(user.get());
            return loginResponse;
    
    }
    

    private LoginResponse buildLoginResponse(UserModel userModel) {

        UserResponse userResponse = UserResponse.builder().createdAt(userModel.getCreatedAt())
                .email(userModel.getEmail()).fullName(userModel.getFullName()).phone(userModel.getPhone())
                .emailVerified(userModel.getEmailVerified()).id(userModel.getId()).build();
        UserDetails userDetails = userDetailsImplementation.loadUserByUsername(userModel.getEmail());
        System.out.println(userDetails);
        String token = jwtService.buildToken(userDetails);
        return LoginResponse.builder().token(token).userResponse(userResponse).build();
    }

}
