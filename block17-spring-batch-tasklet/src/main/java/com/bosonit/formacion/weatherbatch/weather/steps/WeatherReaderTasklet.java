package com.bosonit.formacion.weatherbatch.weather.steps;

import com.bosonit.formacion.weatherbatch.weather.domain.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeatherReaderTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        log.info("----------> Iniciando el Step READER <----------");

        FlatFileItemReader<Weather> fileReader = new FlatFileItemReader<Weather>();
        fileReader.setName("readWeather");
        fileReader.setResource(new ClassPathResource("weather.csv"));
        fileReader.setLinesToSkip(1);
        fileReader.setEncoding(StandardCharsets.UTF_8.name());
        fileReader.setLineMapper(getLineMapper());

        fileReader.open(chunkContext.getStepContext().getStepExecution().getExecutionContext());

        List<Weather> weatherList = new ArrayList<>();
        Weather weather;

        while((weather = fileReader.read()) != null){
            weatherList.add(weather);
        }

        fileReader.close();

        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
                .put("weatherList", weatherList);

        log.info("----------> Finalizando el Step READER <----------");

        return RepeatStatus.FINISHED;
    }

    public LineMapper<Weather> getLineMapper() {
        DefaultLineMapper<Weather> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String[] columns = new String[]{"city", "date", "temperature"};
        int[] indexFields = new int[]{0, 1, 2};

        lineTokenizer.setNames(columns);
        lineTokenizer.setIncludedFields(indexFields);
        lineTokenizer.setDelimiter(":");

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(getFieldSetMapper());

        return lineMapper;
    }

    private FieldSetMapper<Weather> getFieldSetMapper() {
        return new FieldSetMapper<Weather>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public Weather mapFieldSet(FieldSet fieldSet) throws BindException {
                Weather weather = new Weather();
                weather.setCity(fieldSet.readString("city"));
                weather.setDate(fieldSet.readString("date"));
                weather.setTemperature(fieldSet.readInt("temperature"));
                return weather;
            }
        };
    }
}
