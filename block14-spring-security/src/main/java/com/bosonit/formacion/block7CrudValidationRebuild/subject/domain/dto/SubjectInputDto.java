package com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SubjectInputDto {
    @NotEmpty(message = "El campo subjectName debe contener un valor")
    private String subjectName;

    private String comment;

    private LocalDate initialDate;

    private LocalDate finishDate;

    //Relaciones entre tablas
    @NotNull(message = "El campo instructorId debe contener un valor")
    private Integer instructorId;

    private Set<Integer> students = new HashSet<>();
}
