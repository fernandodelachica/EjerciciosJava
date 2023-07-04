package com.bosonit.formacion.block7crudvalidation.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class PersonInputDto {
    int personId;
    String personUser;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    LocalDate createdDate;
    String urlImage;
    LocalDate terminationDate;

}
