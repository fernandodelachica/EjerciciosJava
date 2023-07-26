package com.bosonit.formacion.block7CrudValidationRebuild.mockmvctest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.controller.InstructorController;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.repository.InstructorRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private InstructorRepository instructorRepository;

    @MockBean
    private InstructorController instructorController;


    @Before
    public void init(){
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1);
        instructor.setBranch("Branch1");
        instructor.setComments("Comments1");

        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
    }

    @Test
    public void addInstructor_OK() throws Exception{
        InstructorInputDto instructorInputDto = new InstructorInputDto();
        instructorInputDto.setPersonId(1);
        instructorInputDto.setBranch("Branch1");
        instructorInputDto.setComments("Comments1");

        Instructor newInstructor = new Instructor(instructorInputDto);
        InstructorOutputDto newInstructorOutput = new InstructorOutputDto(newInstructor);

        when(instructorController.addInstructor(any(InstructorInputDto.class))).thenReturn(newInstructorOutput);

        mockMvc.perform(post("/instructor")
                .content(om.writeValueAsString(newInstructorOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.personId", is(1)))
                .andExpect(jsonPath("$.branch", is("Branch1")))
                .andExpect(jsonPath("$.comments", is("Comments1")));

        verify(instructorController, times(1)).addInstructor(any(InstructorInputDto.class));
    }

    @Test
    public void updateInstructor_OK() throws Exception{
        Instructor updateInstructor = new Instructor();
        updateInstructor.setInstructorId(1);
        updateInstructor.setBranch("Branch1");
        updateInstructor.setComments("Comments1");
        updateInstructor.setPerson(new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));

        InstructorOutputDto updatedInstructorOutput = new InstructorOutputDto(updateInstructor);

        when(instructorController.updateInstructorById(anyInt(), any(InstructorInputDto.class))).thenReturn(updatedInstructorOutput);

        mockMvc.perform(put("/instructor/1")
                .content(om.writeValueAsString(updatedInstructorOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.instructorId", is(1)))
                .andExpect(jsonPath("$.branch", is("Branch1")))
                .andExpect(jsonPath("$.comments", is("Comments1")))
                .andExpect(jsonPath("$.personId", is(1)));

        verify(instructorController, times(1)).updateInstructorById(anyInt(), any(InstructorInputDto.class));
    }

    @Test
    public void getAllInstructors_OK() throws Exception{
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add((new Instructor(1, "Comments1", "Branch1", new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null))));
        instructorList.add((new Instructor(2, "Comments2", "Branch2", new Person(2, "Usuario2", "pass123", "Nombre2", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                true, parse("2013-12-03T23:00:00.000+00:00"), "urlImage2.png", parse("2022-04-01T23:00:00.000+00:00"), null, null))));

        List<InstructorOutputDto> instructorOutputDtoList = new ArrayList<>();
        for(Instructor instructor : instructorList){
            instructorOutputDtoList.add(new InstructorOutputDto(instructor));
        }

        when(instructorController.getAllInstructors(anyInt(), anyInt(),anyString())).thenReturn(instructorOutputDtoList);

        mockMvc.perform(get("/instructor")
                .content(om.writeValueAsString(instructorOutputDtoList))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].instructorId", is(1)))
                .andExpect(jsonPath("$[0].comments", is("Comments1")))
                .andExpect(jsonPath("$[0].personId", is(1)))
                .andExpect(jsonPath("$[1].instructorId", is(2)))
                .andExpect(jsonPath("$[1].comments", is("Comments2")))
                .andExpect(jsonPath("$[1].personId", is(2)));

        verify(instructorController, times(1)).getAllInstructors(anyInt(), anyInt(), anyString());
    }

    @Test
    public void deleteInstructorById_OK() throws Exception{
        doNothing().when(instructorController).deleteInstructorById(1);

        mockMvc.perform(delete("/instructor/1"))
                .andExpect(status().isOk());

        verify(instructorController, times(1)).deleteInstructorById(1);
    }

}
