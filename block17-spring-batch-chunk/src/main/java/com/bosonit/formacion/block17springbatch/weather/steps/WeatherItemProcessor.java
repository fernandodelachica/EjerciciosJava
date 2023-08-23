package com.bosonit.formacion.block17springbatch.weather.steps;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import org.springframework.batch.item.ItemProcessor;

public class WeatherItemProcessor implements ItemProcessor<Weather, Weather> {


    @Override
    public Weather process(Weather weather) throws Exception {

        if(weather.getTemperature() > 36){
            weather.setRisk("HIGH");
        }
        if(weather.getTemperature() <= 36){
            weather.setRisk("MEDIUM");
        }
        if(weather.getTemperature() < 24){
            weather.setRisk("LOW");
        }

        return weather;
    }
}
