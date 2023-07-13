package com.formacion.bosonit.block10docker.model;

import com.formacion.bosonit.block10docker.model.dto.StudentInputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    int studentId;
    @Column
    String name;
    @Column
    String lastName;
    @Column(nullable = false)
    Integer numHoursWeek;
    @Column
    String branch;

    public Student(StudentInputDto studentInputDto){
        this.name = studentInputDto.getName();
        this.lastName = studentInputDto.getLastName();
        this.numHoursWeek = studentInputDto.getNumHoursWeek();
        this.branch = studentInputDto.getBranch();
    }
}
