package com.bosonit.formacion.block7crudvalidation.model.dto;

import lombok.*;

@Getter
@Setter
public class StudentInputDto {
    int personId;
    Integer numHoursWeek;
    String comments;
    Integer instructorId;
    String branch;
}
