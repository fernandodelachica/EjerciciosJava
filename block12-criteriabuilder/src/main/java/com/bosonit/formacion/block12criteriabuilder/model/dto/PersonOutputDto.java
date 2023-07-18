package com.bosonit.formacion.block12criteriabuilder.model.dto;

import com.bosonit.formacion.block12criteriabuilder.model.Person;
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

    public PersonOutputDto(Person person) {
        this.personId = person.getPersonId();
        this.personUser = person.getPersonUser();
        this.name = person.getName();
        this.companyEmail = person.getCompanyEmail();
        this.personalEmail = person.getPersonalEmail();
        this.city = person.getCity();
        this.active = person.getActive();
        this.createdDate = person.getCreatedDate();
        this.urlImage = person.getUrlImage();
        this.terminationDate = person.getTerminationDate();

    }
}
