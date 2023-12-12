package com.example.digirealtor.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "users")

public class UserModel {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phone;
    @JsonIgnore
    private String password;
    private Date createdAt;
    @Builder.Default
    private Boolean emailVerified=false;
    private String type;
    @JsonIgnore
    private String propertyId;
}
