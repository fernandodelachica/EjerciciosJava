package com.bosonit.formacion.kafka.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private String name;
    private String city;
    private Integer employeesNumber;
}
