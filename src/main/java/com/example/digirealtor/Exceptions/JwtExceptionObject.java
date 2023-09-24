package com.example.digirealtor.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtExceptionObject {
    
    private String message;
    private String reason;

    
}
