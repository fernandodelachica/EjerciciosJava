package com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer instructorId;

    @Column
    String comments;

    @NotNull
    @Column
    String branch;

    //Relaciones entre tablas
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    Person person;

    @OneToMany(mappedBy = "instructor")
    List<Student> studentList;

    public Instructor(InstructorInputDto instructorInputDto){
        this.comments = instructorInputDto.getComments();

        if(instructorInputDto.getBranch() != null && !instructorInputDto.getBranch().equals("")){
            this.branch = instructorInputDto.getBranch();
        } else throw new UnprocessableEntityException("El campo branch no puede ser nulo");

        this.person = new Person();
        this.person.setPersonId(instructorInputDto.getPersonId());
    }
}
