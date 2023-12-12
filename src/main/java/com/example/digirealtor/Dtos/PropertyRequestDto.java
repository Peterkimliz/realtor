package com.example.digirealtor.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.example.digirealtor.Models.UserModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyRequestDto {
    @NotBlank(message = "name required ")
    private String name;
    @NotBlank(message = "description required ")
    private String description;
    @NotNull(message = "price required ")
    @Min(value = 1, message = "please enter rent price")
    private int rent;
    @NotNull(message = "security required ")
    @Min(value = 1, message = "please enter security")
    private int security;
    @NotEmpty(message = "images required ")

    private List<String> images;
    @NotBlank(message = "category required ")
    private String category;
    @NotNull(message = "bedRooms required ")
    @Min(value = 1, message = "Enter bedRooms")
    private int bedRooms;
    @NotNull(message = "bathRooms required ")
    @Min(value = 1, message = "Enter bathRooms")
    private int bathRooms;
    private int balconies;
    @NotNull(message = "room required ")
    @Min(value = 1, message = "roomSize required")
    private int roomSize;
    private int kitchens;
    @NotBlank(message = "location required ")
    private String location;
    @NotBlank(message = "type required ")
    private String type;
    @Builder.Default
    private List<String> utilitiesList = new ArrayList<>();
    @Builder.Default
    private List<String> appliancesList = new ArrayList<>();
    @Builder.Default
    private List<String> floorCoveringList = new ArrayList<>();
    @Builder.Default
    private List<String> otherList = new ArrayList<>();
    private UserModel owner;

}
