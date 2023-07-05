package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer subjectId;
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="instructorId")
    Instructor instructor;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "studentEnrolled",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    List<Student> students;


    public Subject(SubjectInputDto subjectInputDto){
        this.subjectName = subjectInputDto.getSubjectName();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getInitialDate();
        this.students = getStudents();
    }

    public SubjectOutputDto subjectToSubjectOutputDto() {
        SubjectOutputDto subjectOutputDto = new SubjectOutputDto();
        subjectOutputDto.setSubjectId(this.subjectId);
        subjectOutputDto.setSubjectName(this.subjectName);
        subjectOutputDto.setComment(this.comment);
        subjectOutputDto.setInitialDate(this.initialDate);
        subjectOutputDto.setFinishDate(this.finishDate);
        subjectOutputDto.setStudents(this.students.stream().map(Student::studentToStudentOutputDto).toList()
        );
        return subjectOutputDto;
    }
}
