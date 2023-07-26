package com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInstructorOutpurDto extends PersonOutputDto{

    private Integer instructorId;
    private String comments;
    private String branch;

    public PersonInstructorOutpurDto(Person person){
        super(person);
        Instructor instructor = person.getInstructor();
        this.instructorId = instructor.getInstructorId();
        this.comments = instructor.getComments();
        this.branch = instructor.getBranch();
    }
}
