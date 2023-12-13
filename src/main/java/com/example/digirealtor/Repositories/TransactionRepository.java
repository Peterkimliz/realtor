package com.example.digirealtor.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.digirealtor.Models.Transaction;

public interface TransactionRepository  extends MongoRepository<Transaction,String>{

    @Query("{'tenant.id': ?0}")
    List<Transaction> findByTenant(String tenantId);

    List<Transaction> findByOwner(String ownerId);
     
}
