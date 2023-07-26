package com.bosonit.formacion.block7CrudValidationRebuild.mockmvctest;

import com.bosonit.formacion.block7CrudValidationRebuild.person.controller.PersonController;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.DateUtil.parse;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonController personController;


    @Before
    public void init() {
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
        person.setCreatedDate(parse("2012-12-03"));
        person.setUrlImage("urlImage1.png");
        person.setTerminationDate(parse("2023-04-01"));

        when(personRepository.findById(1)).thenReturn(Optional.of(person));
    }

    @Test
    public void addPerson_OK() throws Exception {
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
        PersonOutputDto newPersonOutput = new PersonOutputDto(newPerson);

        when(personController.addPerson(any(PersonInputDto.class))).thenReturn(newPersonOutput);

        mockMvc.perform(post("/person")
                        .content(om.writeValueAsString(newPersonOutput))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.personUser").value("Usuario1"))
                .andExpect(jsonPath("$.password").value("pass123"))
                .andExpect(jsonPath("$.name").value("Nombre1"))
                .andExpect(jsonPath("$.surname").value("surname1"))
                .andExpect(jsonPath("$.companyEmail").value("company1@email.com"))
                .andExpect(jsonPath("$.personalEmail").value("personal1@email.com"))
                .andExpect(jsonPath("$.city").value("Ciudad1"))
                .andExpect(jsonPath("$.active").value(false))
                .andExpect(jsonPath("$.urlImage").value("urlImage1.png"));

        verify(personController, times(1)).addPerson(any(PersonInputDto.class));

    }

    @Test
    public void updatePerson_OK() throws Exception{
        Person updatePerson = new Person();
        updatePerson.setPersonId(1);
        updatePerson.setPersonUser("Usuario1");
        updatePerson.setPassword("pass123");
        updatePerson.setName("Nombre1");
        updatePerson.setSurname("surname1");
        updatePerson.setCompanyEmail("company1@email.com");
        updatePerson.setPersonalEmail("personal1@email.com");
        updatePerson.setCity("Ciudad1");
        updatePerson.setActive(false);
        updatePerson.setUrlImage("urlImage1.png");

        //Como en el Servicio utilizamos un OutputDto para mostrar los datos, lo simulamos
        PersonOutputDto updatedPersonOutput = new PersonOutputDto(updatePerson);

        when(personController.updatePersonById(anyInt(), any(PersonInputDto.class))).thenReturn(updatedPersonOutput);

        mockMvc.perform(put("/person/1")
                .content(om.writeValueAsString(updatePerson))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personUser").value("Usuario1"))
                .andExpect(jsonPath("$.password").value("pass123"))
                .andExpect(jsonPath("$.name").value("Nombre1"))
                .andExpect(jsonPath("$.surname").value("surname1"))
                .andExpect(jsonPath("$.companyEmail").value("company1@email.com"))
                .andExpect(jsonPath("$.personalEmail").value("personal1@email.com"))
                .andExpect(jsonPath("$.city").value("Ciudad1"))
                .andExpect(jsonPath("$.active").value(false))
                .andExpect(jsonPath("$.urlImage").value("urlImage1.png"));

        verify(personController, times(1)).updatePersonById(anyInt(), any(PersonInputDto.class));
    }

    @Test
    public void getAllPersons_OK() throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));
        personList.add(new Person(2, "Usuario2", "pass123", "Nombre2", "surname2", "company2@email.com", "personal2@email.com", "Ciudad2",
                true, parse("2014-12-03T23:00:00.000+00:00"), "urlImage2.png", parse("2022-04-01T23:00:00.000+00:00"), null, null));


        List<PersonOutputDto> personOutputList = new ArrayList<>();
        for(Person person : personList){
            personOutputList.add(new PersonOutputDto(person));
        }

        when(personController.getAllPersons(anyInt(), anyInt(), anyString())).thenReturn(personOutputList);

        mockMvc.perform(get("/person"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].personId", is (1)))
                .andExpect(jsonPath("$[0].name", is("Nombre1")))
                .andExpect(jsonPath("$[0].active", is(false)))
                .andExpect(jsonPath("$[1].personId", is (2)))
                .andExpect(jsonPath("$[1].name", is("Nombre2")))
                .andExpect(jsonPath("$[1].active", is(true )));

        verify(personController, times(1)).getAllPersons(anyInt(), anyInt(), anyString());
    }

    @Test
    public void deletePersonById_OK() throws Exception {
        doNothing().when(personController).deletePersonById(1);

        mockMvc.perform(delete("/person/1"))
                .andExpect(status().isOk());

        verify(personController, times(1)).deletePersonById(1);
    }
}