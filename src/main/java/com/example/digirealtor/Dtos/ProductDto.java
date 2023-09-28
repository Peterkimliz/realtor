package com.example.digirealtor.Dtos;

import java.util.List;

import com.example.digirealtor.Models.Category;

import jakarta.validation.constraints.NotBlank;
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
      @NotBlank(message = "price  required ")
    private int price;
      @NotBlank(message = "images required ")
    private List<String> images;
      @NotBlank(message = "quantity required ")
    private int quantity;
      @NotBlank(message = "category required ")
    private String category;
      @NotBlank(message = "number of bedrooms required ")
    private int bedRooms;
      @NotBlank(message = "number of bathrooms required ")
    private int bathRooms;
      @NotBlank(message = "number of bulconies required ")
    private int balconies;
      @NotBlank(message = "room size required ")
    private int roomSize;
      @NotBlank(message = "number of kitchens required ")
    private int kitchens;
    private List<Category>amenities;
     @NotBlank(message = "location required ")
    private String location;
      @NotBlank(message = "type required ")
    private String type;
    
}
