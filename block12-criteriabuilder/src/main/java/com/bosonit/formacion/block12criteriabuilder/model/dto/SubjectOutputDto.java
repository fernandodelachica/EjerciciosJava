package com.bosonit.formacion.block12criteriabuilder.model.dto;

import com.bosonit.formacion.block12criteriabuilder.model.Student;
import com.bosonit.formacion.block12criteriabuilder.model.Subject;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class SubjectOutputDto {
    int subjectId;
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;
    Integer instructorId;
    List<Integer> students;


    public SubjectOutputDto(Subject subject){
        this.subjectId = subject.getSubjectId();
        this.subjectName = subject.getSubjectName();
        this.comment = subject.getComment();
        this.initialDate = subject.getInitialDate();
        this.instructorId = subject.getInstructor().getInstructorId();
        this.students = subject.getStudents().stream().map(Student::getStudentId).toList();
    }
}
