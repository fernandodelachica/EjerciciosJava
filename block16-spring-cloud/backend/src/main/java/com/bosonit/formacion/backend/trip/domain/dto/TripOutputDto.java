package com.bosonit.formacion.backend.trip.domain.dto;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import com.bosonit.formacion.backend.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class TripOutputDto {
    private String tripId;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;
    private Set<String> clients;

    public TripOutputDto(Trip trip){
        this.tripId = trip.getTripId();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.status = trip.getStatus();
        this.clients = trip.getPassengers().stream().map(Passenger::getPassengerId).collect(Collectors.toSet());
    }
}
