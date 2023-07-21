package com.bosonit.formacion.block12mongodb.person.controller;

import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonOutputDto;
import com.bosonit.formacion.block12mongodb.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto savePerson(@RequestBody PersonInputDto personInputDto){
        return personService.savePerson(personInputDto);
    }

    @GetMapping
    public List<PersonOutputDto> getAllPerson(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return personService.getAllPerson(pageNumber, pageSize);
    }

    @GetMapping("/id/{id}")
    public PersonOutputDto findOnePersonById(@PathVariable String id){
        return  personService.findOnePersonById(id);
    }

    @GetMapping("/one/{name}")
    public PersonOutputDto findOneByName(@PathVariable String name){
        return personService.findOneByName(name);
    }

    @GetMapping("{name}")
    public List<PersonOutputDto> findByName(@PathVariable String name){
        return personService.findByName(name);
    }

    @GetMapping("/birthdayAfter")
    public List<PersonOutputDto> findByBirthDateAfter(@RequestParam Date date){
        return personService.findByBirthDateAfter(date);
    }

    @GetMapping("/ageRange")
    public List<PersonOutputDto> findByAgeRange(@RequestParam int lowerBound, @RequestParam int upperBound){
        return personService.findByAgeRange(lowerBound, upperBound);
    }

    //Suma +1 a la edad de la Persona con el nombre pasado por el parámetro
    @PutMapping("/updateMultiAge")
    public void updateMultiplePersonAge(@RequestParam String name){
        personService.updateMultiplePersonAge(name);
    }

    @PutMapping("/updateOne/{id}")
    public void updateOnePerson(@PathVariable String id, @RequestBody PersonInputDto personInputDto){
        personService.updateOnePersonById(id, personInputDto);
    }

    @PutMapping("/addFavoriteBooks/{id}")
    public void addFavoriteBooksToPersonById(@PathVariable String id, @RequestBody PersonInputDto personInputDto){
        personService.addFavoriteBooksToPersonById(id, personInputDto);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable String id){
        personService.deletePersonById(id);
    }

    @DeleteMapping("/{id}/favoriteBook")
    public void deleteFavoriteBooksByNameAndId(@PathVariable String  id, @RequestParam List<String> bookNames){
        personService.deleteFavoriteBooksByNameAndId(id, bookNames);
    }

}
