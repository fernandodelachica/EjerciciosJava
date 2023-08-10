package com.bosonit.formacion.backend.passenger.domain;

import com.bosonit.formacion.backend.passenger.domain.dto.PassengerInputDto;
import com.bosonit.formacion.backend.trip.domain.Trip;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    private String passengerId;

    @NotNull
    @Column(name = "Nombre")
    private String name;

    @NotNull
    @Column(name = "Apellido")
    private String lastName;

    @NotNull
    @Column(name = "Edad")
    private Integer age;

    @NotNull
    @Email
    @Column(name = "Email")
    private String email;

    @NotNull
    @Column(name = "Teléfono")
    private Integer telephoneNumber;

    @ManyToMany(mappedBy = "passengers")
    private Set<Trip> trips;

    public Passenger(PassengerInputDto passengerInputDto){
        this.passengerId = UUID.randomUUID().toString();
        this.name = passengerInputDto.getName();
        this.lastName = passengerInputDto.getLastName();
        this.age = passengerInputDto.getAge();
        this.email = passengerInputDto.getEmail();
        this.telephoneNumber = passengerInputDto.getTelephoneNumber();
    }

}
