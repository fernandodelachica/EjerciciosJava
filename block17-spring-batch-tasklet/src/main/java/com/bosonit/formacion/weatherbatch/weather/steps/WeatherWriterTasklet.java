package com.bosonit.formacion.weatherbatch.weather.steps;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import com.bosonit.formacion.weatherbatch.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
public class WeatherWriterTasklet implements Tasklet {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("-----------> Iniciando Step WRITER: Guardando inputs correctos <----------");

        List<Weather> weatherList = (List<Weather>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("weatherList");

        weatherRepository.saveAll(weatherList);

        return RepeatStatus.FINISHED;
    }
}
