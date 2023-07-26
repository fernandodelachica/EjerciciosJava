package com.bosonit.formacion.block7CrudValidationRebuild.mockitotest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.application.SubjectService;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.controller.SubjectController;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectOutputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SubjectControllerTest {

    @InjectMocks
    SubjectController subjectController;

    @Mock
    SubjectService subjectService;

    @Test
    public void addSubject_OK(){
        SubjectInputDto subjectInputDto = new SubjectInputDto();
        subjectInputDto.setSubjectName("SubjectName1");
        subjectInputDto.setComment("Comment1");
        subjectInputDto.setInitialDate(LocalDate.parse("2020-12-01"));
        subjectInputDto.setFinishDate(LocalDate.parse("2023-07-01"));
        subjectInputDto.setInstructorId(1);
        subjectInputDto.setStudents(Arrays.asList(1,2));

        Subject newSubject = new Subject(subjectInputDto);

        Mockito.when(subjectService.addSubject(any(SubjectInputDto.class))).thenReturn(new SubjectOutputDto(newSubject));

        SubjectOutputDto response = subjectController.addSubject(subjectInputDto);

        assertEquals("SubjectName1", response.getSubjectName());
        assertEquals("Comment1", response.getComment());
        assertEquals(LocalDate.parse("2020-12-01"), response.getInitialDate());
        assertEquals(LocalDate.parse("2023-07-01"), response.getFinishDate());

    }

    @Test
    public void updateSubject_OK(){
        Subject subject = new Subject();
        subject.setSubjectId(1);
        subject.setSubjectName("SubjectName1");
        subject.setComment("Comment1");
        subject.setInitialDate(LocalDate.parse("2020-12-01"));
        subject.setFinishDate(LocalDate.parse("2023-07-01"));
        subject.setInstructor(new Instructor(1, "Comments1","Branch1",null,null));
        subject.setStudents(null);

        SubjectInputDto subjectInputDto    = new SubjectInputDto();
        subjectInputDto.setSubjectName("SubjectNameModificado");

        Mockito.when(subjectService.updateSubjectById(anyInt(), any(SubjectInputDto.class))).thenAnswer(invocation ->{
            subject.setSubjectName(subjectInputDto.getSubjectName());
            return new SubjectOutputDto(subject);
        });

        SubjectOutputDto response = subjectController.updateSubject(subject.getSubjectId(), subjectInputDto);

        assertEquals(Integer.valueOf(1), response.getSubjectId());
        assertEquals("SubjectNameModificado", response.getSubjectName());
        assertEquals("Comment1", response.getComment());
        assertEquals(LocalDate.parse("2020-12-01"), response.getInitialDate());
        assertEquals(Integer.valueOf(1), response.getInstructorId());
    }

    @Test
    public void getAllSubjects_OK(){
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(new Subject(1,"SubjectName1","Comment1", LocalDate.parse("2020-12-01"),LocalDate.parse("2023-07-01"),null,null));
        subjectList.add(new Subject(2,"SubjectName2","Comment2", LocalDate.parse("2019-05-01"),LocalDate.parse("2022-12-01"),null,null));


        List<SubjectOutputDto> subjectOutputList = new ArrayList<>();
        for(Subject subject : subjectList){
            subjectOutputList.add(new SubjectOutputDto(subject));
        }

        Mockito.when(subjectService.getAllSubjects(anyInt(), anyInt())).thenReturn(subjectOutputList);

        List<SubjectOutputDto> response = subjectController.getAllSubjects(anyInt(), anyInt());

        assertEquals(Integer.valueOf(1), response.get(0).getSubjectId());
        assertEquals("Comment1", response.get(0).getComment());
        assertEquals(LocalDate.parse("2020-12-01"), response.get(0).getInitialDate());
        assertEquals(Integer.valueOf(2), response.get(1).getSubjectId());
        assertEquals("Comment2", response.get(1).getComment());
        assertEquals(LocalDate.parse("2019-05-01"), response.get(1).getInitialDate());
    }

    @Test
    public void deleteSubjectById_OK(){

        doNothing().when(subjectService).deleteSubjectById(1);

        subjectController.deleteSubjectById(1);

        verify(subjectService, times(1)).deleteSubjectById(1);

    }
}
