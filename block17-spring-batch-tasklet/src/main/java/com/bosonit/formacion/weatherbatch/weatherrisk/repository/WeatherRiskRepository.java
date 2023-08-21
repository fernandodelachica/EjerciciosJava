package com.bosonit.formacion.weatherbatch.weatherrisk.repository;

import com.bosonit.formacion.weatherbatch.weatherrisk.domain.WeatherRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRiskRepository extends JpaRepository<WeatherRisk, Integer> {
    @Query("SELECT wr FROM WeatherRisk wr WHERE wr.city = :city AND wr.year = :year")
    Optional<WeatherRisk> findWeatherRiskByCityAndYear(String city, Integer year);

}
