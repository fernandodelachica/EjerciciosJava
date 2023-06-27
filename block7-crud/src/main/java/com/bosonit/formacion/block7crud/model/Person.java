package com.bosonit.formacion.block7crud.model;

import com.bosonit.formacion.block7crud.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crud.model.dto.PersonOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    int id;
    String name;
    String age;
    String city;

    //Asignación de variables de PersonInput a Person
    public Person(PersonInputDto personInputDto){
        this.id = personInputDto.getId();
        this.name = personInputDto.getName();
        this.age = personInputDto.getAge();
        this.city = personInputDto.getCity();
    }

    //Asignación de variables de Person a PersonOutput
    public PersonOutputDto persontoPersonOutputDto(){
        return new PersonOutputDto(
                this.id,
                this.name,
                this.age,
                this.city
        );
    }
}
