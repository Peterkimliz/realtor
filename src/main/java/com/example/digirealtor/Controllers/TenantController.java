package com.example.digirealtor.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.digirealtor.Dtos.TenantDto;
import com.example.digirealtor.Services.TenantService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tenant")
@Tag(name = "Tenant")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @PostMapping("/create/{propertyId}")
    public ResponseEntity<TenantDto> createTenant(@PathVariable("propertyId") String propertyId,@RequestParam(required = true)String userId,@RequestParam(required = true)String houseNumber) {
           
        return new ResponseEntity<TenantDto>(tenantService.addTenantToBuilding(propertyId, userId,houseNumber), HttpStatus.CREATED);

    }

    @GetMapping("/all/{propertyId}")
    public ResponseEntity<List<TenantDto>> getTenantsofProperty(@PathVariable("propertyId") String propertyId) {
        return new ResponseEntity<List<TenantDto>>(tenantService.getTenantsOfProperty(propertyId), HttpStatus.OK);

    }
    @DeleteMapping("/{tenantId}")
    public ResponseEntity<String> deleteTenant(@PathVariable("tenantId") String tenantId) {
        tenantService.deleteTenantsFromProperty(tenantId);

        return new ResponseEntity<String>("Tenant deleted Successfully", HttpStatus.OK);

    }

}
