package com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto;

import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class SubjectOutputDto {
    private Integer subjectId;
    private String subjectName;
    private String comment;
    private LocalDate initialDate;
    private LocalDate finishDate;
    private Integer instructorId;
    private Set<Integer> students;

    public SubjectOutputDto(Subject subject){
        this.subjectId = subject.getSubjectId();
        this.subjectName = subject.getSubjectName();
        this.comment = subject.getComment();
        this.initialDate = subject.getInitialDate();
        this.finishDate = subject.getFinishDate();
        //Relaciones entre tablas
        if(subject.getInstructor() != null){
            this.instructorId = subject.getInstructor().getInstructorId();
        }
        if(subject.getStudents() != null){
            this.students = subject.getStudents().stream().map(Student::getStudentId).collect(Collectors.toSet());
        }
    }
}
