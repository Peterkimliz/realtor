package com.example.digirealtor.Dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private Date createdAt;
    private Boolean emailVerified;
    
}
