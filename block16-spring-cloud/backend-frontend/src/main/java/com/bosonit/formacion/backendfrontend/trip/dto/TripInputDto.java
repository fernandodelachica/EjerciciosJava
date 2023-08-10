package com.bosonit.formacion.backendfrontend.trip.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TripInputDto {
    private String tripId;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean available;
    private String status;
    private Set<String> clients = new HashSet<>();
}
