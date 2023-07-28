package com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonStudentOutputDto extends PersonOutputDto{

    private Integer studentId;
    private Integer numHoursWeek;
    private String comments;
    private String branch;

    public PersonStudentOutputDto(Person person){
        super(person);
        Student student = person.getStudent();
        this.studentId = student.getStudentId();
        this.numHoursWeek = student.getNumHoursWeek();
        this.branch = student.getBranch();
    }
}
