package com.bosonit.formacion.backend.trip.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TripInputDto {
    @NotBlank(message = "El campo origin no puede ser nulo")
    private String origin;

    @NotBlank(message = "El campo destination no puede ser nulo")
    private String destination;

    @NotNull(message = "El campo departureDate no puede ser nulo")
    private Date departureDate;

    @NotNull(message = "El campo arrivalDate no pude ser nulo")
    private Date arrivalDate;

    private boolean available;
    private Set<String> clients = new HashSet<>();
}