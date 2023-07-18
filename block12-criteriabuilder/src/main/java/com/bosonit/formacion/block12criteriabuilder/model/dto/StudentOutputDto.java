package com.bosonit.formacion.block12criteriabuilder.model.dto;

import com.bosonit.formacion.block12criteriabuilder.model.Student;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    Integer studentId;
    int personId;
    Integer numHoursWeek;
    String comments;
    Integer instructorId;
    String branch;

    public StudentOutputDto(Student student){
        this.studentId = student.getStudentId();
        this.personId = student.getPerson().getPersonId();
        this.numHoursWeek = student.getNumHoursWeek();
        this.comments = student.getComments();
        this.instructorId = student.getInstructor().getInstructorId();
        this.branch = student.getBranch();
    }
}
