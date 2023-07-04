package com.bosonit.formacion.block7crudvalidation.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    int studentId;
    int personId;
    Integer numHoursWeek;
    String comments;
    Integer instructorId;
    String branch;
}
