package com.bosonit.formacion.block17springbatch.weather.steps;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;


import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class WeatherItemReader extends FlatFileItemReader<Weather> {


    public WeatherItemReader(){
        setName("readTemperature");
        setResource(new ClassPathResource("temperature.csv"));
        setLinesToSkip(1);
        setEncoding(StandardCharsets.UTF_8.name());
        setLineMapper(getLineMapper());

    }

    public LineMapper<Weather> getLineMapper(){
        DefaultLineMapper<Weather> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("city", "date", "temperature");
        lineTokenizer.setIncludedFields(0, 1, 2);
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
                weather.setDate(LocalDate.parse(fieldSet.readString("date"), dateFormatter));
                weather.setTemperature(fieldSet.readInt("temperature"));
                return weather;
            }
        };
    }
}
