package com.bosonit.formacion.block7CrudValidationRebuild.person.application;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);

    List<PersonOutputDto> getAllPersons(int pageSize, int pageNumber, String outputType);

    PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto);

    List<PersonOutputDto> getAllPersonsByUser(String personUser);

    void deletePersonById(int id);

    //RestTemplate
    InstructorOutputDto getInstructor(int id);


}
