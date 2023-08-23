package com.bosonit.formacion.block17springbatch.weather.steps;

import com.bosonit.formacion.block17springbatch.weather.application.WeatherService;
import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import com.bosonit.formacion.block17springbatch.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class WeatherItemWriter implements ItemWriter<Weather> {

    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    public void write(List<? extends Weather> list) throws Exception {

        boolean filterApproved = true;

        //List da problemas con los hilos esta opción es una implementación de List para funcionamiento en hilos
        CopyOnWriteArrayList<Weather> acceptedWeatherList = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Weather> deniedWeatherList = new CopyOnWriteArrayList<>();

        for(Weather weather : list ){
            if(weather.getTemperature() > 50 || weather.getTemperature() < (-20)){
                deniedWeatherList.add(weather);
            } else {
                acceptedWeatherList.add(weather);
            }
        }

        if(deniedWeatherList.size() > 100){
            filterApproved = false;
        }

        if(filterApproved){

            writeDeniedTemperaturesToFile(deniedWeatherList);
            weatherRepository.saveAll(acceptedWeatherList);

        } else {
            log.error("Error, Más de 100 errores en el archivo.");
            weatherRepository.deleteAll(acceptedWeatherList);
        }
    }

    private void writeDeniedTemperaturesToFile(List<Weather> deniedWeatherList) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (FileWriter csvWriter = new FileWriter("src/main/resources/ERROR_Temperatures.csv")) {
            csvWriter.append("city:date:temperature\n");
            for (Weather weather : deniedWeatherList) {
                csvWriter.append(weather.getCity())
                        .append(":")
                        .append(weather.getDate().format(formatter))
                        .append(":")
                        .append(Double.toString(weather.getTemperature()))
                        .append("\n");
            }
            int errorNumber = deniedWeatherList.size();
            log.info("Hay "+errorNumber+" inputs con errores.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
