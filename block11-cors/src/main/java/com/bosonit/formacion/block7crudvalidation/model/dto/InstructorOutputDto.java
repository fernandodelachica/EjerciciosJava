package com.bosonit.formacion.block7crudvalidation.model.dto;


import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorOutputDto {
    Integer instructorId;
    int personId;
    String comments;
    String branch;

    public InstructorOutputDto(Instructor instructor) {
        this.instructorId = instructor.getInstructorId();
        this.personId = instructor.getPerson().getPersonId();
        this.comments = instructor.getComments();
        this.branch = instructor.getBranch();
    }
}
