package com.example.digirealtor.Models;

import java.util.ArrayList;
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
@Document(collection = "property")

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
    @Builder.Default
    private int balconies = 0;
    private int roomSize;
    @Builder.Default
    private int kitchens = 0;
    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Category> utilities = new ArrayList<>();
    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Category> appliances = new ArrayList<>();
    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Category> floorCovering = new ArrayList<>();
    @DocumentReference(lazy = true)
    @Builder.Default
    private List<Category> others = new ArrayList<>();
    private Date createdAt;
    private String location;
    private String type;
    private boolean featured;
    private Boolean deleted;
    @DocumentReference(lazy = true)
    private UserModel agent;

}
