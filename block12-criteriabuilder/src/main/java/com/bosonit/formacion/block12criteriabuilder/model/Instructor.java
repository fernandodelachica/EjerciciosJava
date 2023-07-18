package com.bosonit.formacion.block12criteriabuilder.model;

import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorOutputDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer instructorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    Person person;

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
                this.person.getPersonId(),
                this.comments,
                this.branch
        );
    }

}
