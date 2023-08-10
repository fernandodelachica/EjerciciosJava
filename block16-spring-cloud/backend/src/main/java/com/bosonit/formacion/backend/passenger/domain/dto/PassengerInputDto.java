package com.bosonit.formacion.backend.passenger.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PassengerInputDto {
    @NotBlank(message = "El campo name no puede ser nulo")
    private String name;

    @NotBlank(message = "El campo lastName no puede ser nulo")
    private String lastName;

    @NotNull(message = "El campo age no puede ser nulo")
    private Integer age;

    @Email(message = "Formato de email no válido")
    @NotBlank(message = "El campo email no puede ser nulo")
    private String email;

    @NotNull(message = "El campo telephoneNumber no puede ser nulo")
    private Integer telephoneNumber;
}
