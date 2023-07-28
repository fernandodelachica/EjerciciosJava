package com.bosonit.formacion.block7CrudValidationRebuild.mockitotest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.application.InstructorService;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.controller.InstructorController;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.DateUtil.parse;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InstructorControllerTest {
    @InjectMocks
    InstructorController instructorController;

    @Mock
    InstructorService instructorService;

    @Test
    public void addInstructor_OK(){
        InstructorInputDto instructorInputDto = new InstructorInputDto();
        instructorInputDto.setComments("Comments1");
        instructorInputDto.setBranch("Branch1");
        instructorInputDto.setPersonId(1);

        Instructor newInstructor = new Instructor(instructorInputDto);

        Mockito.when(instructorService.addInstructor(any(InstructorInputDto.class))).thenReturn(new InstructorOutputDto(newInstructor));

        InstructorOutputDto response = instructorController.addInstructor(instructorInputDto);

        assertEquals("Comments1", response.getComments());
        assertEquals("Branch1", response.getBranch());
        assertEquals(Integer.valueOf(1), response.getPersonId());
    }

    @Test
    public void updateInstructor_OK(){
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1);
        instructor.setComments("Comments1");
        instructor.setBranch("Branch1");
        instructor.setPerson(
                new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                        false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));

        InstructorInputDto instructorInputDto = new InstructorInputDto();
        instructorInputDto.setBranch("BranchModificado");

        Mockito.when(instructorService.updateInstructorById(anyInt(), any(InstructorInputDto.class))).thenAnswer(invocation ->{
            instructor.setBranch(instructorInputDto.getBranch());
            return new InstructorOutputDto(instructor);
        });

        InstructorOutputDto response = instructorController.updateInstructorById(instructor.getInstructorId(), instructorInputDto);

        assertEquals(Integer.valueOf(1), response.getInstructorId());
        assertEquals("Comments1", response.getComments());
        assertEquals("BranchModificado", response.getBranch());
        assertEquals(Integer.valueOf(1), response.getPersonId());
    }

    @Test
    public void getAllInstructors_OK(){
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(new Instructor(1, "Comments1","Branch1",null,null));
        instructorList.add(new Instructor(2, "Comments2","Branch2",null,null));


        List<InstructorOutputDto> instructorOutputList = new ArrayList<>();
        for(Instructor instructor : instructorList){
            instructorOutputList.add(new InstructorOutputDto(instructor));
        }

        Mockito.when(instructorService.getAllInstructors(anyInt(), anyInt(), anyString())).thenReturn(instructorOutputList);

        List<InstructorOutputDto> response = instructorController.getAllInstructors(anyInt(), anyInt(), anyString());

        assertEquals(Integer.valueOf(1), response.get(0).getInstructorId());
        assertEquals("Comments1", response.get(0).getComments());
        assertEquals("Branch1", response.get(0).getBranch());
        assertEquals(Integer.valueOf(2), response.get(1).getInstructorId());
        assertEquals("Comments2", response.get(1).getComments());
        assertEquals("Branch2", response.get(1).getBranch());
    }

    @Test
    public void deleteInstructorById_OK(){

        doNothing().when(instructorService).deleteInstructorById(1);

        instructorController.deleteInstructorById(1);

        verify(instructorService, times(1)).deleteInstructorById(1);

    }

}
