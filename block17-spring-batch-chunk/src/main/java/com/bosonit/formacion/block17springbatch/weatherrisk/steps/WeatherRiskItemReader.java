package com.bosonit.formacion.block17springbatch.weatherrisk.steps;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import com.bosonit.formacion.block17springbatch.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class WeatherRiskItemReader implements ItemReader<Weather> {

    @Autowired
    private WeatherRepository weatherRepository;

    private Iterator<Weather> weatherIterator;


    @Override
    public Weather read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("----------> Iniciando Step READER de Weather para WeatherRisk <----------");


        if(weatherIterator == null){
            List<Weather> weatherData = weatherRepository.findAll();
            weatherIterator = weatherData.iterator();
        }

        log.info("----------> Finalizando Step READER de Weather para WeatherRisk <----------");

        return weatherIterator.hasNext() ? weatherIterator.next() : null;
    }
}
