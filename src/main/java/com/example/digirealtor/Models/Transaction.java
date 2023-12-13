package com.example.digirealtor.Models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Transaction")
@Builder

public class Transaction {
    @Id
  private String id;
  private int amount;
  private Date createdAt;
  @DocumentReference(lazy = true)
  private Tenant tenant;
  private String propertyId;
  private String transactionId;
  private String owner;
    
}
