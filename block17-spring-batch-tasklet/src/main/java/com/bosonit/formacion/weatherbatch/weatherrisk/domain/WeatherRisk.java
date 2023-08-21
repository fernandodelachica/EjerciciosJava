package com.bosonit.formacion.weatherbatch.weatherrisk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "weather_risk")
public class WeatherRisk{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer weatherRiskId;
    private String city;
    private Integer year;
    private Integer entriesNumber;
    private double averageTemperature;
    private String risk;


}
