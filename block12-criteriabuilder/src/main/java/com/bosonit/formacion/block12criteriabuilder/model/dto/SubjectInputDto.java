package com.bosonit.formacion.block12criteriabuilder.model.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
