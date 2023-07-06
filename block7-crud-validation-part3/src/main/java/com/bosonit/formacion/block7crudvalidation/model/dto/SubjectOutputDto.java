package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
