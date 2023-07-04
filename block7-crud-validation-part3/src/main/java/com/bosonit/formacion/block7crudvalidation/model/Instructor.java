package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorOutputDto;
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
public class Instructor {
    @Id
    @GeneratedValue
    Integer instructorId;

    @OneToOne
    @JoinColumn(name = "personId")
    public Person person;

    String comments;
    @Column(name = "branch", nullable = false)
    String branch;

    @OneToMany(mappedBy = "instructor")
    List<Student> studentList;

    public Instructor (InstructorInputDto instructorInputDto){
        this.person.setPersonId(instructorInputDto.getPersonId());
        this.comments = instructorInputDto.getComments();
        this.branch = instructorInputDto.getBranch();
    }

    public InstructorOutputDto instructorToInstructorOutputDto(){
        return new InstructorOutputDto(
                this.instructorId,
                this.person.personId,
                this.comments,
                this.branch
        );
    }

}