package com.bosonit.formacion.block17springbatch.config;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import com.bosonit.formacion.block17springbatch.weather.steps.*;
import com.bosonit.formacion.block17springbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.block17springbatch.weatherrisk.steps.WeatherRiskItemProcessor;
import com.bosonit.formacion.block17springbatch.weatherrisk.steps.WeatherRiskItemReader;
import com.bosonit.formacion.block17springbatch.weatherrisk.steps.WeatherRiskItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public WeatherItemReader weatherItemReader(){
        return new WeatherItemReader();
    }

    @Bean
    public WeatherItemProcessor weatherItemProcessor(){
        return new WeatherItemProcessor();
    }

    @Bean
    public WeatherItemWriter weatherItemWriter(){
        return new WeatherItemWriter();
    }

    @Bean
    public WeatherRiskItemReader weatherRiskItemReader(){
        return new WeatherRiskItemReader();
    }

    @Bean
    public WeatherRiskItemProcessor weatherRiskItemProcessor(){
        return new WeatherRiskItemProcessor();
    }

    @Bean
    public WeatherRiskItemWriter weatherRiskItemWriter(){
        return new WeatherRiskItemWriter();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    @Bean
    public Step readCsvWeatherFileStep(){
        return stepBuilderFactory.get("readCsvWeatherFileStep")
                .<Weather, Weather>chunk(200)
                .reader(weatherItemReader())
                .processor(weatherItemProcessor())
                .writer(weatherItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step weatherToWeatherRiskStep(){
        return stepBuilderFactory.get("weatherToWeatherRiskStep")
                .<Weather, WeatherRisk>chunk(200)
                .reader(weatherRiskItemReader())
                .processor(weatherRiskItemProcessor())
                .writer(weatherRiskItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean(name = "readCsvFileJob")
    public Job readCsvFileJob(){
        return jobBuilderFactory.get("readCsvFileJob")
                .start(readCsvWeatherFileStep())
                .next(weatherToWeatherRiskStep())
                .build();
    }

}
