package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int studentId;

    @OneToOne
    @JoinColumn(name = "personId")
    Person person;

    @Column(name = "num_hours_week", nullable = false)
    Integer numHoursWeek;

    String comments;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    Instructor instructor;

    @Column(name = "branch", nullable = false)
    String branch;

    @ManyToMany
    @JoinTable(name = "subject",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "topicId")
    )
    List<Subject> subjectList;


    public Student(StudentInputDto studentInputDto){
        this.person.setPersonId(studentInputDto.getPersonId());
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.comments = studentInputDto.getComments();
        if (studentInputDto.getInstructorId() != null){
            this.instructor.setInstructorId(studentInputDto.getInstructorId());
        }
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(
          this.studentId,
          this.person.personId,
          this.numHoursWeek,
          this.comments,
          this.instructor.instructorId,
          this.branch
        );
    }
}
