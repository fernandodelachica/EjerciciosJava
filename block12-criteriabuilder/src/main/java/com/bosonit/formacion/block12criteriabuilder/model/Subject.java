package com.bosonit.formacion.block12criteriabuilder.model;

import com.bosonit.formacion.block12criteriabuilder.model.dto.SubjectInputDto;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer subjectId;
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="instructorId")
    Instructor instructor;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    Set<Student> students;



    public Subject(SubjectInputDto subjectInputDto){
        this.subjectName = subjectInputDto.getSubjectName();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getInitialDate();
    }

}
