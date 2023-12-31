package com.example.digirealtor.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    @NotBlank(message = "name required")
    private String name;

    @NotBlank(message = "type required")
    private String type;
    private String id;

}
