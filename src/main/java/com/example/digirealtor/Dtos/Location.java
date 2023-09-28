package com.example.digirealtor.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private String ipAddress;
    private String device;
    private String city;
    private String fullLocation;
    private double latitude;
    private double longitude;
}
