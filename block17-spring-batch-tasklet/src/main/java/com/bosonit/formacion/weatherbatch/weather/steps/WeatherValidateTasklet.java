package com.bosonit.formacion.weatherbatch.weather.steps;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import com.bosonit.formacion.weatherbatch.weather.repository.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeatherValidateTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Iniciando Step VALIDATION <----------");

        List<Weather> weatherList = (List<Weather>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("weatherList");

        boolean filterApproved = true;

        List<Weather> approvedWeatherList = new ArrayList<>();
        List<Weather> deniedWeatherList = new ArrayList<>();

        for (Weather weather : weatherList){
            if(weather.getTemperature() > 50 || weather.getTemperature() < -20){
                deniedWeatherList.add(weather);
            } else {
                approvedWeatherList.add(weather);
            }
        }

        if(deniedWeatherList.size() >= 100){
            filterApproved = false;
        }

        ExitStatus exitStatus;
        if(filterApproved){
            exitStatus = new ExitStatus("VALID");
            stepContribution.setExitStatus(exitStatus);
        } else {
            exitStatus = new ExitStatus("INVALID");
            stepContribution.setExitStatus(exitStatus);
        }

        //Lo mandamos al contexto con el mismo nombre para sobreescribir el existente y ahorrar espacio
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put("weatherList", approvedWeatherList);

        //Mandamos al contexto una nueva lista para posteriormente utilizarla para escribir los errores
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put("deniedWeatherList", deniedWeatherList);

        log.info("----------> Finalizado Step VALIDATION <----------");

        return RepeatStatus.FINISHED;
    }
}
