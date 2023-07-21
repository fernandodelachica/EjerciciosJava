package com.bosonit.formacion.block12mongodb.person.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonInputDto {
    private String name;
    private Integer age;
    private List<String> favoriteBooks;
    private Date dateOfBirth;
}
