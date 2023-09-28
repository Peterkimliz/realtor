package com.example.digirealtor.Dtos;

import java.util.List;

import com.example.digirealtor.Models.Category;

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
public class ProductDto {
    @NotBlank(message = "name required ")
    private String name;
    @NotBlank(message = "description required ")
    private String description;
    @NotNull(message = "price required ")
    @Min(value = 1, message = "please enter valid price")
    private int price;
    @NotEmpty(message = "images required ")
    private List<String> images;
    @NotNull(message = "price quantity ")
    @Min(value = 1, message = "please enter valid quantity")
    @NotBlank(message = "quantity required ")
    private int quantity;
    @NotBlank(message = "category required ")
    private String category;
    @NotNull(message = "bedRooms required ")
    @Min(0)
    private int bedRooms;
    @NotNull(message = "bathRooms required ")
    @Min(0)
    private int bathRooms;
    @NotNull(message = "balconies required ")
    @Min(0)
    private int balconies;
    @NotNull(message = "room required ")
    @Min(1)
    private int roomSize;
    @NotNull(message = "kitchens required ")
    @Min(1)
    private int kitchens;
    private List<Category> amenities;
    @NotBlank(message = "location required ")
    private String location;
    @NotBlank(message = "type required ")
    private String type;

}
