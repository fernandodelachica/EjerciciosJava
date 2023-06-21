package com.bosonit.formacion.block6personcontrollers.service;

import java.util.ArrayList;
import java.util.List;

import com.bosonit.formacion.block6personcontrollers.dto.City;

public class CityService {
    private List<City> citiesList = new ArrayList<>();

    public void addCity(City city){
        citiesList.add(city);
    }

    public List<City> getCitiesList(){
        return citiesList;
    }

}
