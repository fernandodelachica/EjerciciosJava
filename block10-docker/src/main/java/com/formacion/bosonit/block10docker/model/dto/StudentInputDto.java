package com.formacion.bosonit.block10docker.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInputDto {

    String name;
    String lastName;
    Integer numHoursWeek;
    String branch;
}
