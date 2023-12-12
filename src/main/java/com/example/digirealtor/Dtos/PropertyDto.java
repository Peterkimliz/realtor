package com.example.digirealtor.Dtos;

import java.util.List;

import com.example.digirealtor.Models.Category;
import com.example.digirealtor.Models.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyDto {

    private String id;
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
    private String location;
    private String type;
    private List<Category> utilities;
    private List<Category> appliances;
    private List<Category> floorCovering;
    private List<Category> other;
    private UserModel owner;

}
