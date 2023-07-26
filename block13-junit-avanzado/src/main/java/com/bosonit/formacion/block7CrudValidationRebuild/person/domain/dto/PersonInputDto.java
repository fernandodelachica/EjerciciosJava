package com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PersonInputDto {

    @NotBlank(message = "El campo personUser debe contener un valor")
    @Size(min = 6, max = 10, message = "El usuario debe tener entre 6 y 10 caracteres")
    private String personUser;

    @NotEmpty(message = "El campo password debe contener un valor")
    private String password;

    @NotEmpty(message = "El campo name debe contener un valor")
    private String name;

    private String surname;

    @NotEmpty(message = "El campo companyEmail debe contener un valor")
    private String companyEmail;

    @NotEmpty(message = "El campo personalEmail debe contener un valor")
    @Email(message = "Email inválido")
    private String personalEmail;

    @NotEmpty(message = "El campo createdDate debe contener un valor")
    private String city;

    private Boolean active;

    private Date createdDate;

    private String urlImage;

    private Date terminationDate;
}
