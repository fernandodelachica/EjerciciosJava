package com.bosonit.formacion.block7crudvalidation.model.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorOutputDto {
    Integer instructorId;
    int personId;
    String comments;
    String branch;
}
