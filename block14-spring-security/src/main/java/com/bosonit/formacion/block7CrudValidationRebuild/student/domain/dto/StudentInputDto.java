package com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDto {

    @NotNull(message = "El campo numHoursWeek debe contener un valor")
    private Integer numHoursWeek;
    private String comments;

    @NotEmpty(message = "El campo branch debe contener un valor")
    private String branch;

    //Relaciones entre tablas
    private Integer personId;
    private Integer instructorId;
}
