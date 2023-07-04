package com.bosonit.formacion.block7crudvalidation.model.dto;

import lombok.*;


@Getter
@Setter
public class InstructorInputDto {
    Integer instructorId;
    int personId;
    String comments;
    String branch;
}
