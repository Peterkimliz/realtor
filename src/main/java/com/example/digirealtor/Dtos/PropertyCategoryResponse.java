package com.example.digirealtor.Dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PropertyCategoryResponse {
    private String name;
    private String id;
    private Date createdAt;
    @Builder.Default
    private List<CategoryDto> categoryDto=new ArrayList<>();
}
