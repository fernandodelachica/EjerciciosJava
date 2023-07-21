package com.bosonit.formacion.block12mongodb.person.domain.dto;

import com.bosonit.formacion.block12mongodb.person.domain.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PersonOutputDto {
    @Id
    private String personId;
    private String name;
    private Integer age;
    private List<String> favoriteBooks;
    private Date dateOfBirth;

    public PersonOutputDto(Person person){
        this.personId = person.getPersonId();
        this.name = person.getName();
        this.age = person.getAge();
        this.favoriteBooks = person.getFavoriteBooks();
        this.dateOfBirth = person.getDateOfBirth();
    }
}
