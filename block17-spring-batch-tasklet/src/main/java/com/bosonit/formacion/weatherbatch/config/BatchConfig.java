package com.bosonit.formacion.weatherbatch.config;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import com.bosonit.formacion.weatherbatch.weather.steps.*;
import com.bosonit.formacion.weatherbatch.weatherrisk.domain.WeatherRisk;
import com.bosonit.formacion.weatherbatch.weatherrisk.steps.WeatherRiskItemProcessor;
import com.bosonit.formacion.weatherbatch.weatherrisk.steps.WeatherRiskItemReader;
import com.bosonit.formacion.weatherbatch.weatherrisk.steps.WeatherRiskItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
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
    public WeatherReaderTasklet weatherReaderTasklet(){
        return new WeatherReaderTasklet();
    }

    @Bean
    public WeatherValidateTasklet weatherValidateTasklet(){
        return new WeatherValidateTasklet();
    }

    @Bean
    public WeatherWriterTasklet weatherWriterTasklet(){
        return new WeatherWriterTasklet();
    }

    @Bean
    public WeatherErrorCSVWriterTasklet weatherErrorCSVWriterTasklet(){
        return new WeatherErrorCSVWriterTasklet();
    }

    @Bean
    public WeatherCancelTransactionTasklet weatherCancelTransactionTasklet(){
        return new WeatherCancelTransactionTasklet();
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
    public Step weatherReadCsvStep(){
        return stepBuilderFactory.get("weatherReadCsvStep")
                .tasklet(weatherReaderTasklet())
                .build();
    }

    @Bean
    @JobScope
    public Step weatherValidateStep(){
        return stepBuilderFactory.get("weatherValidateStep")
                .tasklet(weatherValidateTasklet())
                .build();
    }

    @Bean
    public Step weatherWriterStep(){
        return stepBuilderFactory.get("weatherWriterStep")
                .tasklet(weatherWriterTasklet())
                .build();
    }
    @Bean
    public Step weatherErrorCsvWriterStep(){
        return stepBuilderFactory.get("weatherErrorCsvWriterStep")
                .tasklet(weatherErrorCSVWriterTasklet())
                .build();
    }
    @Bean
    public Step weatherCancelTransactionStep(){
        return stepBuilderFactory.get("weatherCancelTransactionStep")
                .tasklet(weatherCancelTransactionTasklet())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    public Step weatherToWeatherRiskStep(){
        return stepBuilderFactory.get("weatherToWeatherRiskStep")
                .<Weather, WeatherRisk>chunk(200)
                .reader(weatherRiskItemReader())
                .processor(weatherRiskItemProcessor())
                .writer(weatherRiskItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job weatherReaderJob(){
        return jobBuilderFactory.get("weatherReaderJob")
                .start(weatherReadCsvStep())
                .next(weatherValidateStep())
                    .on("VALID").to(weatherWriterStep())
                    .next(weatherErrorCsvWriterStep())
                    .next(weatherToWeatherRiskStep())
                .from(weatherValidateStep())
                    .on("INVALID").to(weatherCancelTransactionStep())
                    .next(weatherErrorCsvWriterStep())
                .end()
                .build();
    }
}
