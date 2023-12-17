package com.example.digirealtor.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PropertyCategory")
public class PropertyCategory {
    @Id
    private String id;
    private String name;
    private Date createdAt;
    private List<Category> categories=new ArrayList<>();

    
}
