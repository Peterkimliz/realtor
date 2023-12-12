package com.example.digirealtor.Dtos;

import java.util.Date;

import com.example.digirealtor.Models.Tenant;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionRequestDto {
    @NotBlank(message = "amount required")
    private int amount;
    @NotBlank(message = "property id required ")
    private String propertyId;
    @NotBlank(message = "transaction id required ")
    private String transactionId;
  

}
