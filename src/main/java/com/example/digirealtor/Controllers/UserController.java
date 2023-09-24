package com.example.digirealtor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.UserResponse;
import com.example.digirealtor.Services.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") String userId) {
        return new ResponseEntity<UserResponse>(userService.getUserById(userId), HttpStatus.OK);
    }

}
