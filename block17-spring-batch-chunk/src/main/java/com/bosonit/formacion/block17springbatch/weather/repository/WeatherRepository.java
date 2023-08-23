package com.bosonit.formacion.block17springbatch.weather.repository;

import com.bosonit.formacion.block17springbatch.weather.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    @Query("SELECT AVG(w.temperature) FROM Weather w WHERE w.city = :city AND YEAR(w.date) = :year")
    Double findAverageTemperatureByCityAndYear(String city, int year);

    @Query("SELECT COUNT(w) FROM Weather w WHERE w.city = :city AND YEAR(w.date) = :year")
    Integer countWeatherInputsByCityAndYear(String city, int year);

}
