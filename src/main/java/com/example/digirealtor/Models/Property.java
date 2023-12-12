package com.example.digirealtor.Models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "product")

public class Property {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private UserModel owner;
    private String name;
    private String description;
    private int rent;
    private int security;
    private List<String> images;
    private String category;
    private int bedRooms;
    private int bathRooms;
    private int balconies;
    private int roomSize;
    private int kitchens;
    @DocumentReference(lazy = true)
    private List<Category> utilities;
    private List<Category> appliances;
    private List<Category> floorCovering;
    private List<Category> others;
    private Date createdAt;
    private String location;
    private String type;
    private boolean featured;
    private Boolean deleted;

    @DocumentReference(lazy = true)
    private UserModel agent;

}
