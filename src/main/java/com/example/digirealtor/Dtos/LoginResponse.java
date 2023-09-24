package com.example.digirealtor.Dtos;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private UserResponse userResponse;
    
}
