package com.bosonit.formacion.block7CrudValidationRebuild.subject.domain;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private Integer subjectId;

    @NotNull
    @Column
    private String subjectName;

    @Column
    private String comment;

    @Column
    private LocalDate initialDate;

    @Column
    private LocalDate finishDate;


    //Relaciones entre tablas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId")
    Instructor instructor;


    @ManyToMany
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "studentId"))
    Set<Student> students;

    public Subject(SubjectInputDto subjectInputDto){
        this.subjectName = subjectInputDto.getSubjectName();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getFinishDate();
    }
}
