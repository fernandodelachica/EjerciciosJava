package com.bosonit.formacion.weatherbatch.weatherrisk.steps;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import com.bosonit.formacion.weatherbatch.weather.repository.WeatherRepository;
import com.bosonit.formacion.weatherbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.weatherbatch.weatherrisk.repository.WeatherRiskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class WeatherRiskItemProcessor implements ItemProcessor<Weather, WeatherRisk> {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherRiskRepository weatherRiskRepository;


    @Override
    public WeatherRisk process(Weather weather) throws Exception {

        log.info("----------> Iniciando Step PROCESSOR de Weather <----------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String city = weather.getCity();
        LocalDate date = LocalDate.parse(weather.getDate());
        Integer year = date.getYear();

        //Calcular temperatura media y determinar el riesgo
        double averageTemperature = weatherRepository.findAverageTemperatureByCityAndYear(city, year);
        Integer countInputs = weatherRepository.countWeatherInputsByCityAndYear(city, year);

        WeatherRisk weatherRisk = new WeatherRisk();
        weatherRisk.setCity(city);
        weatherRisk.setYear(year);
        weatherRisk.setAverageTemperature(averageTemperature);
        weatherRisk.setEntriesNumber(countInputs);
        weatherRisk.setRisk(determineRiskLevel(averageTemperature, weatherRisk));


        log.info("----------> Finalizando Step PROCESSOR de Weather <----------");

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
