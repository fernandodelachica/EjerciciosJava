package com.bosonit.formacion.weatherbatch.weather.steps;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WeatherErrorCSVWriterTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Iniciando Step WRITER: Generando CSV inputs incorrectos <----------");

        List<Weather> deniedWeatherList = (List<Weather>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("deniedWeatherList");

        try (FileWriter csvWriter = new FileWriter("src/main/resources/weather_errors.csv")){
            csvWriter.append("city:date:temperature\n");

            for(Weather weather : deniedWeatherList){
                csvWriter.append(weather.getCity())
                        .append(":")
                        .append(weather.getDate().toString())
                        .append(":")
                        .append(Double.toString(weather.getTemperature()))
                        .append("\n");
            }
            int errorNum = deniedWeatherList.size();

            log.info("-------> Finalizado Step Writer: Número total de errores: "+errorNum+" <----------");
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return RepeatStatus.FINISHED;
    }
}
