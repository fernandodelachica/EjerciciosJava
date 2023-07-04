package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Subject;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SubjectInputDto {
    Integer instructorId;
    List<Integer> studentsIds = new ArrayList<>();
    String topic;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;

}
