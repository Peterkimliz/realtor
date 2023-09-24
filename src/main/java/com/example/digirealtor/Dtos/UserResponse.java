package com.example.digirealtor.Dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {
    private String fullName;
    private String email;
    private String phone;
    private Date createdAt;
    private boolean emailVerified;
}
