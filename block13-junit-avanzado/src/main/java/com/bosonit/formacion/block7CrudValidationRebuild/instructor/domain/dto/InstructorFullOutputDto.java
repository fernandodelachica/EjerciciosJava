package com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InstructorFullOutputDto extends InstructorOutputDto{

    Integer personId;
    String personUser;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String urlImage;
    Date terminationDate;

    public InstructorFullOutputDto(Instructor instructor){
        super(instructor);
        Person person = instructor.getPerson();
        this.personId = person.getPersonId();
        this.personUser = person.getPersonUser();
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
