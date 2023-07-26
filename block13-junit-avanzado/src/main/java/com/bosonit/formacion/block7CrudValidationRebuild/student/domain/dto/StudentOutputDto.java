package com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentOutputDto {
    private Integer studentId;
    private Integer numHoursWeek;
    private String comments;
    private String branch;
    //Relaciones entre tablas
    private Integer personId;
    private Integer instructorId;


    public StudentOutputDto(Student student){
        this.studentId = student.getStudentId();
        this.numHoursWeek = student.getNumHoursWeek();
        this.comments = student.getComments();
        this.branch = student.getBranch();
        //Relaciones entre tablas
        if(student.getPerson() != null){
            this.personId = student.getPerson().getPersonId();
        }
        if(student.getInstructor() != null){
            this.instructorId = student.getInstructor().getInstructorId();
        }
    }
}
