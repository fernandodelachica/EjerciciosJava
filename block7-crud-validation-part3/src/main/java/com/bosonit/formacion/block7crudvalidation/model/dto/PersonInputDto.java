package com.bosonit.formacion.block7crudvalidation.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class PersonInputDto {

    @NotBlank(message = "El User es obligatorio")
    @Size(max = 10, min = 6, message = "El User debe tener entre 6 y 10 caracteres.")
    String personUser;

    @NotBlank(message = "La contraseña es obligatoria, por favor introduzca una válida")
    String password;

    @NotBlank(message = "El nombre es obligatorio, por favor introduzca uno válido")
    String name;
    String surname;

    @NotBlank(message = "El email corporativo es obligatorio, por favor introduzca uno válido")
    @Email(message = "Formato de email corporativo incorrecto")
    String companyEmail;

    @NotBlank(message = "El email personal es obligatorio, por favor introduzca uno válido")
    @Email(message = "Formato de email personal incorrecto")
    String personalEmail;

    @NotBlank(message = "El campo ciudad está vacío")
    String city;

    Boolean active;
    LocalDate createdDate;
    String urlImage;
    LocalDate terminationDate;

}
