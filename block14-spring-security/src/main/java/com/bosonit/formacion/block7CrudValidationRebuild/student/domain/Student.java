package com.bosonit.formacion.block7CrudValidationRebuild.student.domain;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Integer studentId;

    @NotNull
    @Column
    private Integer numHoursWeek;

    @Column
    private String comments;

    @NotNull
    @Column
    private String branch;

    //Relación entre tablas
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId")
    private Instructor instructor;

    @ManyToMany
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId"))
    Set<Subject> subjects;


    public Student(StudentInputDto studentInputDto){
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.comments = studentInputDto.getComments();
        this.branch = studentInputDto.getBranch();
        //Relaciones entre tablas
        this.person = new Person();
        this.person.setPersonId(studentInputDto.getPersonId());
        if (studentInputDto.getInstructorId() != null){
            this.instructor = new Instructor();
            this.instructor.setInstructorId(studentInputDto.getInstructorId());
        }
    }


}
