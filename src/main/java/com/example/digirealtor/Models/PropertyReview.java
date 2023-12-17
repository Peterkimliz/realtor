package com.example.digirealtor.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "propertyReview")
public class PropertyReview {
    @Id
    private String id;
    private String message;
    private int rating;
    private String propertyId;
    @DocumentReference(lazy = true)
    private UserModel reviewer;
}
