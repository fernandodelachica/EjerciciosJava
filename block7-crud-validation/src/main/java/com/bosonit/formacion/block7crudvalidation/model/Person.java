package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Person(PersonInputDto personInputDto){
        this.personId = personInputDto.getPersonId();
        this.personUser = personInputDto.getPersonUser();
        this.password = personInputDto.getPassword();
        this.name = personInputDto.getName();
        this.surname = personInputDto.getSurname();
        this.companyEmail = personInputDto.getCompanyEmail();
        this.personalEmail = personInputDto.getPersonalEmail();
        this.city = personInputDto.getCity();
        this.active = personInputDto.getActive();
        this.createdDate = personInputDto.getCreatedDate();
        this.urlImage = personInputDto.getUrlImage();
        this.terminationDate = personInputDto.getTerminationDate();
    }

    public PersonOutputDto persontoPersonOutputDto(){
        return new PersonOutputDto(
                this.personId,
                this.personUser,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.urlImage,
                this.terminationDate
        );
    }



}
