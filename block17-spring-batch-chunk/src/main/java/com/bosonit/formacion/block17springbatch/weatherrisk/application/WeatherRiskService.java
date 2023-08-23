package com.bosonit.formacion.block17springbatch.weatherrisk.application;

import com.bosonit.formacion.block17springbatch.weatherrisk.domain.WeatherRisk;

import java.util.List;

public interface WeatherRiskService {

    Iterable<WeatherRisk> saveAll(List<WeatherRisk> weatherList);
}
