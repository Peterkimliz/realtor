package com.example.digirealtor.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Dtos.TenantDto;
import com.example.digirealtor.Exceptions.FoundException;
import com.example.digirealtor.Exceptions.NotFoundException;
import com.example.digirealtor.Models.Tenant;
import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.TenantRepository;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class TenantService {
    @Autowired
    TenantRepository tenantRepository;
    @Autowired
    UserRepository userRepository;

    public TenantDto addTenantToBuilding(String propertyId, String tenantId, String houseNumber) {
        Optional<UserModel> user = userRepository.findById(tenantId);
        if (!user.isPresent()) {
            throw new NotFoundException("Tenant not found in the system");
        }
        Optional<Tenant> tenOptional = tenantRepository.findByTenantAndPropertyId(tenantId, propertyId);

        if (tenOptional.isPresent()) {
            throw new FoundException("Tenant already assigned a house");
        }

        Tenant tenants = Tenant.builder().createdAt(new Date(System.currentTimeMillis())).propertyId(propertyId)
                .tenant(user.get()).houseNumber(houseNumber).build();

        tenantRepository.save(tenants);
        TenantDto tenantDto = TenantDto.builder().createdAd(tenants.getCreatedAt()).tenant(tenants.getTenant())
                .propertyId(tenants.getPropertyId()).houseNumber(tenants.getHouseNumber()).id(tenants.getId()).build();

        return tenantDto;

    }

    public List<TenantDto> getTenantsOfProperty(String propertyId) {

        List<Tenant> tenants = tenantRepository.findByPropertyId(propertyId);
        if (tenants.size() > 0) {

            return tenants.stream().map(e -> TenantDto.builder().createdAd(e.getCreatedAt()).id(e.getId())
                    .propertyId(e.getPropertyId()).tenant(e.getTenant()).houseNumber(e.getHouseNumber()).build()).toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteTenantsFromProperty(String tenantId) {

        Optional<Tenant> tenants = tenantRepository.findById(tenantId);
        if (tenants.isPresent()) {
            tenantRepository.deleteById(tenantId);

        } else {
            throw new NotFoundException("Tenant with id not found");
        }
    }

}
