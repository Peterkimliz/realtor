package com.example.digirealtor.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PropertyCategoryRequestDto {
    @NotBlank(message = "name required")
    private String name;

}
