package com.bosonit.formacion.block12criteriabuilder.model;

import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonOutputDto;
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
    private int personId;

    @Column
    private String personUser;

    @Column
    String password;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String companyEmail;

    @Column
    String personalEmail;

    @Column
    String city;

    @Column
    Boolean active;

    @Column
    LocalDate createdDate;

    @Column
    String urlImage;

    @Column
    LocalDate terminationDate;

    @OneToOne(mappedBy ="person")
    Student student;

    @OneToOne(mappedBy ="person")
    Instructor instructor;


    public Person(PersonInputDto personInputDto){
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
