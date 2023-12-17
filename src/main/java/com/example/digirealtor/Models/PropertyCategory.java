package com.example.digirealtor.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PropertyCategory")
@Builder
public class PropertyCategory {
    @Id
    private String id;
    private String name;
    private Date createdAt;
    @Builder.Default
    private List<Category> categories=new ArrayList<>();

    
}
