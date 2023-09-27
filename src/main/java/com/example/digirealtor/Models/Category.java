package com.example.digirealtor.Models;

import java.sql.Date;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "categories")

public class Category {
    @Id
    private String id;
    @UniqueElements
    private String name;
    private Date createdAt;

    
}
