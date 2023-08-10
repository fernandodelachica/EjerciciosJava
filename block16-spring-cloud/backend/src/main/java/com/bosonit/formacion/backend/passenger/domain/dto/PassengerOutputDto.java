package com.bosonit.formacion.backend.passenger.domain.dto;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerOutputDto {
    private String passengerId;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private Integer telephoneNumber;

    public PassengerOutputDto(Passenger passenger){
        this.passengerId = passenger.getPassengerId();
        this.name = passenger.getName();
        this.lastName = passenger.getLastName();
        this.age = passenger.getAge();
        this.email = passenger.getEmail();
        this.telephoneNumber = passenger.getTelephoneNumber();
    }
}
