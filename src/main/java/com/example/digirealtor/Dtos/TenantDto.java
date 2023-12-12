package com.example.digirealtor.Dtos;

import java.util.Date;

import com.example.digirealtor.Models.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantDto {
    private String propertyId;
    private UserModel tenant;
    private String id;
    private Date createdAd;
    private String houseNumber;
}
