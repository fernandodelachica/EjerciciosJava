package com.bosonit.formacion.block17springbatch.weatherrisk.steps;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import com.bosonit.formacion.block17springbatch.weather.repository.WeatherRepository;
import com.bosonit.formacion.block17springbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.block17springbatch.weatherrisk.repository.WeatherRiskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WeatherRiskItemProcessor implements ItemProcessor<Weather, WeatherRisk> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherRiskRepository weatherRiskRepository;


    @Override
    public WeatherRisk process(Weather weather) throws Exception {

        log.info("----------> Iniciando Step PROCESSOR de WeatherRisk <----------");
        String city = weather.getCity();
        Integer year = weather.getDate().getYear();

        //Calcular temperatura media y determinar el riesgo
        double averageTemperature = weatherRepository.findAverageTemperatureByCityAndYear(city, year);
        Integer countInputs = weatherRepository.countWeatherInputsByCityAndYear(city, year);

        WeatherRisk weatherRisk = new WeatherRisk();
        weatherRisk.setCity(city);
        weatherRisk.setYear(year);
        weatherRisk.setAverageTemperature(averageTemperature);
        weatherRisk.setMeasurementsNumber(countInputs);
        weatherRisk.setRisk(determineRiskLevel(averageTemperature, weatherRisk));


        log.info("----------> Finalizando Step PROCESSOR de WeatherRisk <----------");

        return weatherRisk;
    }

    private String determineRiskLevel(double averageTemperature, WeatherRisk weatherRisk){
        if (averageTemperature > 36){
            weatherRisk.setRisk("HIGH");
        } else if (averageTemperature <= 36 && averageTemperature >= 10){
            weatherRisk.setRisk("MEDIUM");
        } else {
            weatherRisk.setRisk("LOW");
        }

        return weatherRisk.getRisk();
    }
}
