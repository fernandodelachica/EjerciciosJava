package com.formacion.bosonit.block10docker.model.dto;

import com.formacion.bosonit.block10docker.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    int studentId;
    String name;
    String lastName;
    Integer numHoursWeek;
    String branch;


    public StudentOutputDto(Student student){
        this.studentId = student.getStudentId();
        this.name = student.getName();
        this.lastName = student.getLastName();
        this.numHoursWeek = student.getNumHoursWeek();
        this.branch = student.getBranch();
    }
}
