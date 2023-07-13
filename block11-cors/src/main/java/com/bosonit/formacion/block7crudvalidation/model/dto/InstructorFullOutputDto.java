package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InstructorFullOutputDto extends InstructorOutputDto{
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


    public InstructorFullOutputDto(Instructor instructor){
        super(instructor);
        Person person = instructor.getPerson();
        this.personId = person.getPersonId();
        this.personUser = person.getPersonUser();
        this.password = person.getPassword();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.companyEmail = person.getCompanyEmail();
        this.personalEmail = person.getPersonalEmail();
        this.city = person.getCity();
        this.active = person.getActive();
        this.createdDate = person.getCreatedDate();
        this.urlImage = person.getUrlImage();
        this.terminationDate = person.getTerminationDate();
    }
}
