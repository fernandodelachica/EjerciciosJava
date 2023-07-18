package com.bosonit.formacion.block12criteriabuilder.model;

import com.bosonit.formacion.block12criteriabuilder.model.dto.StudentInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    Person person;

    @Column(name = "num_hours_week", nullable = false)
    Integer numHoursWeek;

    @Column
    String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId")
    Instructor instructor;

    @Column(name = "branch", nullable = false)
    String branch;

    @ManyToMany(mappedBy = "students")
    Set<Subject> subjects;


    public Student(StudentInputDto studentInputDto){
        this.person.setPersonId(studentInputDto.getPersonId());
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.comments = studentInputDto.getComments();
        if (studentInputDto.getInstructorId() != null){
            this.instructor.setInstructorId(studentInputDto.getInstructorId());
        }
        this.branch = studentInputDto.getBranch();
    }

}
