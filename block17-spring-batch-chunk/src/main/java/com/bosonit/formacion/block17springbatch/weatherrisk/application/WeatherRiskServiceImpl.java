package com.bosonit.formacion.block17springbatch.weatherrisk.application;

import com.bosonit.formacion.block17springbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.block17springbatch.weatherrisk.repository.WeatherRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherRiskServiceImpl implements  WeatherRiskService{

    @Autowired
    private WeatherRiskRepository weatherRiskRepository;

    @Override
    public Iterable<WeatherRisk> saveAll(List<WeatherRisk> weatherList){
        return weatherRiskRepository.saveAll(weatherList);
    }
}
