package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SubjectInputDto {
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;

    Integer instructorId;
    List<Integer> students = new ArrayList<>();

}
