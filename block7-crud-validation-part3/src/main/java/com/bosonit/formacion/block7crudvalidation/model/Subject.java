package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer studentTopicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId")
    Instructor instructor;

    String topic;
    String comment;

    @Column(name = "initialDate")
    LocalDate initialDate;

    LocalDate finishDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subject")
    List<Student > students = new ArrayList<>();


    public Subject(SubjectInputDto subjectInputDto){
        this.topic = subjectInputDto.getTopic();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getFinishDate();
        //this.students
        //this.instructor
    }

}
