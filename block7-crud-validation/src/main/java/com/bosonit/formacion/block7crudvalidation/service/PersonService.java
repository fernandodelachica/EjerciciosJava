package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;
import org.hibernate.action.internal.EntityActionVetoException;

import java.util.List;

public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException;

    PersonOutputDto updatePerson(int id, PersonInputDto person) throws UnprocessableEntityException;

    PersonOutputDto getPersonById(int id) throws EntityNotFoundException;

    List<PersonOutputDto> getAllPerson();

    List<PersonOutputDto> getAllPersonByUser(String personUser);
}
