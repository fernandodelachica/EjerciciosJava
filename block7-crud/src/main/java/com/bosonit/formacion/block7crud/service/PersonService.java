package com.bosonit.formacion.block7crud.service;

import com.bosonit.formacion.block7crud.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crud.model.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {
    //Servicio de Añadir personas a Person
    PersonOutputDto addPerson(PersonInputDto person);

    PersonOutputDto updatePerson(int id, PersonInputDto person);

    void deletePersonById(int id);

    PersonOutputDto getPersonById(int id);

    List<PersonOutputDto> getAllPersonByNameLike(String name);

    List<PersonOutputDto> getAllPerson();
}
