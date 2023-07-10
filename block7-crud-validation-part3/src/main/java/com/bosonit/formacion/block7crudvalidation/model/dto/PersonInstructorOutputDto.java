package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Person;

public class PersonInstructorOutputDto extends PersonOutputDto {

    public InstructorOutputDto instructor;

    public PersonInstructorOutputDto(Person person){
        super(person);
    }

    public void setInstructor(InstructorOutputDto instructorOutputDto){
        this.instructor = instructorOutputDto;
    }
}
