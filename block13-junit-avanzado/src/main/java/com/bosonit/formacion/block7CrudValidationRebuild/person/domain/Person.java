package com.bosonit.formacion.block7CrudValidationRebuild.person.domain;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Integer personId;

    @NotNull
    @Column
    private String personUser;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String name;

    @Column
    private String surname;

    @NotNull
    @Column
    private String companyEmail;

    @NotNull
    @Column
    private String personalEmail;

    @Column
    private String city;

    @NotNull
    @Column
    private Boolean active;

    @NotNull
    @Column
    private Date createdDate;

    @Column
    private String urlImage;

    @Column
    private Date terminationDate;

    //Relación entre tablas
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Instructor instructor;



    public Person(PersonInputDto personInputDto){
        if(personInputDto.getPersonUser() != null){
            if(personInputDto.getPersonUser().length() > 10 || personInputDto.getPersonUser().length() < 5){
                throw new UnprocessableEntityException("El campo personUser tiene que tener entre 5 y 10 caracteres");
            } else this.personUser = personInputDto.getPersonUser();
        } else throw new UnprocessableEntityException("El campo personUser no puede ser nulo");

        if(personInputDto.getPassword() != null && !personInputDto.getPassword().equals("")){
            this.password = personInputDto.getPassword();
        } else throw new UnprocessableEntityException("password no puede ser nulo");

        if(personInputDto.getName() != null && !personInputDto.getName().equals("")){
            this.name = personInputDto.getName();
        } else throw new UnprocessableEntityException("name no puede ser nulo");

        this.surname = personInputDto.getSurname();

        if(personInputDto.getCompanyEmail() != null && !personInputDto.getCompanyEmail().equals("")){
            this.companyEmail = personInputDto.getCompanyEmail();
        } else  throw new UnprocessableEntityException("companyEmail no puede ser nulo");

        if(personInputDto.getPersonalEmail() != null && !personInputDto.getPersonalEmail().equals("")){
            this.personalEmail = personInputDto.getPersonalEmail();
        } else throw new UnprocessableEntityException("personalEmail no puede ser nulo");

        if(personInputDto.getCity() != null && !personInputDto.getCity().equals("")){
            this.city = personInputDto.getCity();
        }else throw new UnprocessableEntityException("city no puede ser nulo");

        if(personInputDto.getActive() != null){
            this.active = personInputDto.getActive();
        }else throw new UnprocessableEntityException("active no puede ser nulo");

        if(personInputDto.getCreatedDate() != null){
            this.createdDate = personInputDto.getCreatedDate();
        } else throw new UnprocessableEntityException("createdDate no puede ser nulo");

        this.urlImage = personInputDto.getUrlImage();
        this.terminationDate = personInputDto.getTerminationDate();
    }



}
