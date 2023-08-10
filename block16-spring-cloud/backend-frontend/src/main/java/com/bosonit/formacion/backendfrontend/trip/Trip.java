package com.bosonit.formacion.backendfrontend.trip;

import com.bosonit.formacion.backendfrontend.passenger.domain.Passenger;
import com.bosonit.formacion.backendfrontend.trip.dto.TripInputDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Trip {
    private String tripId;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean available;
    private String status;
    private Set<Passenger> passengers;

    public Trip(TripInputDto tripInputDto){
        this.tripId = UUID.randomUUID().toString();
        this.origin = tripInputDto.getOrigin();
        this.destination = tripInputDto.getDestination();
        this.departureDate = tripInputDto.getDepartureDate();
        this.arrivalDate = tripInputDto.getArrivalDate();
        this.available = tripInputDto.isAvailable();
        this.status = tripInputDto.getStatus();
    }
}
