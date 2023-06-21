package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.dto.City;
import com.bosonit.formacion.block6personcontrollers.dto.Person;
import com.bosonit.formacion.block6personcontrollers.service.CityService;
import com.bosonit.formacion.block6personcontrollers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller1")
public class Controller1 {
    @Autowired
    PersonService personService;
    @Autowired
    CityService cityService;

    @GetMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestHeader("name") String name,
                                            @RequestHeader("city") String city,
                                            @RequestHeader("age") int age){
        Person person = personService.createPerson(name, city, age);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/addCity")
    public ResponseEntity<Void> addCity(@RequestBody City city){
        cityService.addCity(city);
        return ResponseEntity.ok().build();
    }

}
