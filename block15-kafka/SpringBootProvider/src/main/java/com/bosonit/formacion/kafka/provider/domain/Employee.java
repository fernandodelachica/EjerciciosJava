package com.bosonit.formacion.kafka.provider.domain;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String lastName;
    private String job;
    private Integer salaryPerYear;
    private Integer salaryPerMonth;
}
