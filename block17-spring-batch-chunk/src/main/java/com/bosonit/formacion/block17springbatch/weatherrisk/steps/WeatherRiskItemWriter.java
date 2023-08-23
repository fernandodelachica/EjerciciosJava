package com.bosonit.formacion.block17springbatch.weatherrisk.steps;

import com.bosonit.formacion.block17springbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.block17springbatch.weatherrisk.repository.WeatherRiskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class WeatherRiskItemWriter implements ItemWriter<WeatherRisk> {

    @Autowired
    private WeatherRiskRepository weatherRiskRepository;


    @Override
    public void write(List<? extends WeatherRisk> list) throws Exception {
        log.info("----------> Iniciando Step WRITER de WeatherRisk <----------");

        for (WeatherRisk weatherRisk : list) {
            String city = weatherRisk.getCity();
            Integer year = weatherRisk.getYear();

            Optional<WeatherRisk> existingWeatherRisk = weatherRiskRepository.findWeatherRiskByCityAndYear(city, year);

            if (existingWeatherRisk.isPresent()) {
                WeatherRisk existing = existingWeatherRisk.get();
                // Actualiza los campos si ya existe
                existing.setAverageTemperature(weatherRisk.getAverageTemperature());
                existing.setRisk(weatherRisk.getRisk());
                existing.setMeasurementsNumber(weatherRisk.getMeasurementsNumber());
                weatherRiskRepository.save(existing);
            } else {
                //Si no existe la fila crea un nuevo registro
                weatherRiskRepository.save(weatherRisk);
            }
        }
        log.info("----------> Finalizando Step WRITER de WeatherRisk <----------");

    }
}
