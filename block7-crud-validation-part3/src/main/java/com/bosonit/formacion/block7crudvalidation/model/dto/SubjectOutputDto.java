package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutputDto {
    int subjectId;
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;
    List<StudentOutputDto> students;



    //Integer instructorId;
    //List<Integer> students = new ArrayList<>();



}
