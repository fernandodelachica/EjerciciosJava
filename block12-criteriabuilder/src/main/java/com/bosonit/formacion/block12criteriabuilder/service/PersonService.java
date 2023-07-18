package com.bosonit.formacion.block12criteriabuilder.service;

import com.bosonit.formacion.block12criteriabuilder.exception.UnprocessableEntityException;
import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorOutputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException;

    PersonOutputDto updatePerson(int id, PersonInputDto person) throws UnprocessableEntityException;

    List<PersonOutputDto> getAllPerson();

    List<PersonOutputDto> getAllPersonByUser(String personUser);

    List<PersonOutputDto> getPersons(String outputType);

    PersonOutputDto getPersonById(int id, String outputType);

    InstructorOutputDto getInstructor(int id);
}
