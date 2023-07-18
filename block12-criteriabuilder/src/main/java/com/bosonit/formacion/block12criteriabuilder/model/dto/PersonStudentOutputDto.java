package com.bosonit.formacion.block12criteriabuilder.model.dto;

import com.bosonit.formacion.block12criteriabuilder.model.Person;

public class PersonStudentOutputDto extends PersonOutputDto{

    public StudentOutputDto student;

    public PersonStudentOutputDto(Person person){
        super(person);
    }

    public void setStudent(StudentOutputDto studentOutputDto){
        this.student = studentOutputDto;
    }
}
