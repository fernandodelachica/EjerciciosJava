package com.bosonit.formacion.block17springbatch.weatherrisk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRisk{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer weatherId;
        private String city;
        private Integer year;
        private Integer measurementsNumber;
        private double averageTemperature;
        private String risk;
}
