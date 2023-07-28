package com.bosonit.formacion.block7CrudValidationRebuild.mockitotest;

import com.bosonit.formacion.block7CrudValidationRebuild.person.application.PersonService;
import com.bosonit.formacion.block7CrudValidationRebuild.person.controller.PersonController;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.DateUtil.parse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @InjectMocks
    PersonController personController;

    @Mock
    PersonService personService;

    @Test
    public void addPerson_OK(){
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setPersonUser("Usuario1");
        personInputDto.setPassword("pass123");
        personInputDto.setName("Nombre1");
        personInputDto.setSurname("surname1");
        personInputDto.setCompanyEmail("company1@email.com");
        personInputDto.setPersonalEmail("personal1@email.com");
        personInputDto.setCity("Ciudad1");
        personInputDto.setActive(false);
        personInputDto.setUrlImage("urlImage1.png");

        Person newPerson = new Person(personInputDto);

        Mockito.when(personService.addPerson(any(PersonInputDto.class))).thenReturn(new PersonOutputDto(newPerson));

        PersonOutputDto response = personController.addPerson(personInputDto);

        assertEquals("Usuario1", response.getPersonUser());
        assertEquals("pass123", response.getPassword());
        assertEquals("Nombre1", response.getName());
        assertEquals("surname1", response.getSurname());
        assertEquals("company1@email.com", response.getCompanyEmail());
        assertEquals("personal1@email.com", response.getPersonalEmail());
        assertEquals("Ciudad1", response.getCity());
        assertEquals(false, response.getActive());
        assertEquals("urlImage1.png", response.getUrlImage());
    }

    @Test
    public void updatePerson_OK(){
        Person person = new Person();
        person.setPersonId(1);
        person.setPersonUser("Usuario1");
        person.setPassword("pass123");
        person.setName("Nombre1");
        person.setSurname("surname1");
        person.setCompanyEmail("company1@email.com");
        person.setPersonalEmail("personal1@email.com");
        person.setCity("Ciudad1");
        person.setActive(false);
        person.setUrlImage("urlImage1.png");

        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setPersonUser("UsuarioModificado");

        Mockito.when(personService.updatePersonById(anyInt(), any(PersonInputDto.class))).thenAnswer(invocation ->{
            person.setPersonUser(personInputDto.getPersonUser());
            return new PersonOutputDto(person);
                });

        PersonOutputDto response = personController.updatePersonById(person.getPersonId(), personInputDto);

        assertEquals("UsuarioModificado", response.getPersonUser());
        assertEquals("pass123", response.getPassword());
        assertEquals("Nombre1", response.getName());
        assertEquals("surname1", response.getSurname());
        assertEquals("company1@email.com", response.getCompanyEmail());
        assertEquals("personal1@email.com", response.getPersonalEmail());
        assertEquals("Ciudad1", response.getCity());
        assertEquals(false, response.getActive());
        assertEquals("urlImage1.png", response.getUrlImage());
    }

    @Test
    public void getAllPersons_OK(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));
        personList.add(new Person(2, "Usuario2", "pass123", "Nombre2", "surname2", "company2@email.com", "personal2@email.com", "Ciudad2",
                true, parse("2014-12-03T23:00:00.000+00:00"), "urlImage2.png", parse("2022-04-01T23:00:00.000+00:00"), null, null));


        List<PersonOutputDto> personOutputList = new ArrayList<>();
        for(Person person : personList){
            personOutputList.add(new PersonOutputDto(person));
        }

        Mockito.when(personService.getAllPersons(anyInt(), anyInt(), anyString())).thenReturn(personOutputList);

        List<PersonOutputDto> response = personController.getAllPersons(anyInt(), anyInt(), anyString());

        assertEquals(Integer.valueOf(1), response.get(0).getPersonId());
        assertEquals("Usuario1", response.get(0).getPersonUser());
        assertEquals("Nombre1", response.get(0).getName());
        assertEquals(Integer.valueOf(2), response.get(1).getPersonId());
        assertEquals("Usuario2", response.get(1).getPersonUser());
        assertEquals("Nombre2", response.get(1).getName());
    }

    @Test
    public void deletePersonById_OK(){

        doNothing().when(personService).deletePersonById(1);

        personController.deletePersonById(1);

        verify(personService, times(1)).deletePersonById(1);

    }


}
