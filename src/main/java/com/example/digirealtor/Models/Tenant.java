package com.example.digirealtor.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document("tenants")
public class Tenant {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private UserModel tenant;
    private String propertyId;
    private Date createdAt;
    private String houseNumber;

}
