package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws Exception;

    PersonOutputDto getPersonById(int id);

    List<PersonOutputDto> getAllPerson();

    List<PersonOutputDto> getAllPersonByUser(String personUser);
}
