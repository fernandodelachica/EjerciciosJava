package com.bosonit.formacion.backend.trip.domain;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import com.bosonit.formacion.backend.trip.domain.dto.TripInputDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    private String tripId;
    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    private Date departureDate;
    @NotNull
    private Date arrivalDate;
    private boolean available;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "trips_passengers",
            joinColumns = @JoinColumn(name = "tripId"),
            inverseJoinColumns = @JoinColumn(name = "passengerId")
    )
    private Set<Passenger> passengers;


    public Trip(TripInputDto tripInputDto){
        this.tripId = UUID.randomUUID().toString();
        this.origin = tripInputDto.getOrigin();
        this.destination = tripInputDto.getDestination();
        this.departureDate = tripInputDto.getDepartureDate();
        this.arrivalDate = tripInputDto.getArrivalDate();
        this.available = tripInputDto.isAvailable();
    }


}
