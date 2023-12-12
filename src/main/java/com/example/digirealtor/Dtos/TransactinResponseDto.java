package com.example.digirealtor.Dtos;

import java.util.Date;

import com.example.digirealtor.Models.Tenant;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactinResponseDto {
    private Tenant tenant;
    private String id;
    private Date createdAt;
    TransactionRequestDto transactionRequestDto;
    
}
