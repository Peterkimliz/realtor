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

public class Product {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    private UserModel owner;
    private String name;
    private String description;
    private int price;
    private List<String> images;
    private int quantity;
    private String category;
    private int bedRooms;
    private int bathRooms;
    private int balconies;
    private int roomSize;
    private int kitchens;
    @DocumentReference(lazy = true)
    private List<Category>amenities;
    private Date createdAt;
    private String location;
    private String type;
    private boolean featured;

    
}
