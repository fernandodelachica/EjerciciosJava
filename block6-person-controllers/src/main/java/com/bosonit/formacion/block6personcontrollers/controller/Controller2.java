package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.dto.City;
import com.bosonit.formacion.block6personcontrollers.dto.Person;
import com.bosonit.formacion.block6personcontrollers.service.CityService;
import com.bosonit.formacion.block6personcontrollers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controller2")
public class Controller2 {
    @Autowired
    PersonService personService;
    @Autowired
    CityService cityService;

    @GetMapping("/getPerson")
    public ResponseEntity<Person> getPerson(){
        Person person = personService.getPerson();
        person.setAge(person.getAge()*2);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/getCities")
    public ResponseEntity<List<City>> getCitiesList(){
        List<City> citiesList = cityService.getCitiesList();
        return ResponseEntity.ok(citiesList);
    }
}
