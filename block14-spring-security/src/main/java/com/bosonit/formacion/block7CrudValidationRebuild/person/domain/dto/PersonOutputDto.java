package com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter

public class PersonOutputDto {
    private Integer personId;
    private String personUser;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String urlImage;
    private Date terminationDate;
    private Set<String> roles;

    public PersonOutputDto(Person person){
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
        this.roles = person.getRoles().stream().map(role -> role.getRoleName().toString()).collect(Collectors.toSet());
    }
}
