package com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class StudentFullOutputDto extends StudentOutputDto{

    private Integer personId;
    private String personUser;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String urlImage;
    private Date terminationDate;

    public StudentFullOutputDto(Student student){
        super(student);
        Person person = student.getPerson();
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
