package com.bosonit.formacion.backendfrontend.passenger.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerInputDto {
    private String passengerId;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private Integer telephoneNumber;
}
