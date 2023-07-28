package com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorInputDto {

    private String comments;

    @NotEmpty(message = "El campo branch no puede ser nulo")
    private String branch;

    //Relaciones entre tablas
    private Integer personId;
}
