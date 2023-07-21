package com.bosonit.formacion.block12mongodb.person.service;

import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonOutputDto;

import java.util.Date;
import java.util.List;

public interface PersonService {

    PersonOutputDto savePerson(PersonInputDto personInputDto);

    List<PersonOutputDto> getAllPerson(int pageNumber, int pageSize);

    PersonOutputDto findOnePersonById(String id);

    PersonOutputDto findOneByName(String name);

    List<PersonOutputDto> findByName(String name);

    List<PersonOutputDto> findByBirthDateAfter(Date date);

    List<PersonOutputDto> findByAgeRange(int lowerBound, int upperBound);

    void updateMultiplePersonAge(String name);

    void updateOnePersonById(String id, PersonInputDto personInputDto);

    void addFavoriteBooksToPersonById(String id, PersonInputDto personInputDto);

    void deletePersonById(String id);

    void deleteFavoriteBooksByNameAndId(String id, List<String> bookNames);
}
