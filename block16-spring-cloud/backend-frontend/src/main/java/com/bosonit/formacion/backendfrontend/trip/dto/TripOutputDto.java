package com.bosonit.formacion.backendfrontend.trip.dto;

import com.bosonit.formacion.backendfrontend.trip.Trip;
import com.bosonit.formacion.backendfrontend.passenger.domain.Passenger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TripOutputDto {
    private String tripId;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean available;
    private String status;
    private Set<String> clients;

}
