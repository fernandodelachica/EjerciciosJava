package com.bosonit.formacion.block12mongodb.person.domain;


import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonInputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person {
    @Id
    private String personId;
    private String name;
    private Integer age;
    private List<String> favoriteBooks;
    private Date dateOfBirth;

    public Person(PersonInputDto personInputDto){
        this.name = personInputDto.getName();
        this.favoriteBooks = personInputDto.getFavoriteBooks();
        this.dateOfBirth = personInputDto.getDateOfBirth();
        if (personInputDto.getAge() == null){
            this.age = calculateAge(personInputDto);
        } else this.age = personInputDto.getAge();
    }


    private Integer calculateAge(PersonInputDto personInputDto){
        if (dateOfBirth != null){
            LocalDate birthDate = personInputDto.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        } return null;
    }

}
