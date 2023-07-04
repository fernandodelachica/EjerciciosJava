package com.bosonit.formacion.block7crudvalidation.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    int personId;
    String personUser;
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
