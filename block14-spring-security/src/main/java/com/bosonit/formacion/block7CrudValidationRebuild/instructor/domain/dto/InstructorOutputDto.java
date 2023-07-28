package com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorOutputDto {
    Integer instructorId;
    String comments;
    String branch;
    //Relaciones entre tablas
    Integer personId;

    public InstructorOutputDto(Instructor instructor){
        this.instructorId = instructor.getInstructorId();
        this.comments = instructor.getComments();
        this.branch = instructor.getBranch();
        //Relaciones entre tablas
        if(instructor.getPerson() != null){
            this.personId = instructor.getPerson().getPersonId();
        }
    }
}
