package com.bosonit.formacion.backendfrontend.passenger.domain;

import com.bosonit.formacion.backendfrontend.trip.Trip;
import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerInputDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class Passenger {
    private String passengerId;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private Integer telephoneNumber;
    private Set<Trip> trips;

    public Passenger(PassengerInputDto passengerInputDto){
        this.passengerId = passengerInputDto.getPassengerId();
        this.name = passengerInputDto.getName();
        this.lastName = passengerInputDto.getLastName();
        this.age = passengerInputDto.getAge();
        this.email = passengerInputDto.getEmail();
        this.telephoneNumber = passengerInputDto.getTelephoneNumber();
    }
}
