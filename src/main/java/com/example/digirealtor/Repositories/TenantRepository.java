package com.example.digirealtor.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.digirealtor.Models.Tenant;

@Repository
public interface TenantRepository extends MongoRepository<Tenant, String> {

    @Query("{'tenant.id': ?0,'propertyId': ?1}")
    Optional<Tenant> findByTenantAndPropertyId(String tenantId, String propertyId);

    List<Tenant> findByPropertyId(String propertyId);

}
