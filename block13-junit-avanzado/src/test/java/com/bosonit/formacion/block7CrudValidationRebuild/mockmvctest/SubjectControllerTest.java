package com.bosonit.formacion.block7CrudValidationRebuild.mockmvctest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.controller.SubjectController;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.repository.SubjectRepository;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.util.DateUtil.parse;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @MockBean
    SubjectController subjectController;

    @MockBean
    SubjectRepository subjectRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    @Before
    public void init(){
        Subject subject = new Subject();
        subject.setSubjectId(1);
        subject.setSubjectName("SubjectName1");
        subject.setComment("Comment1");
        subject.setFinishDate(LocalDate.parse("2022-12-01"));
        subject.setInitialDate(LocalDate.parse("2020-01-01"));
        subject.setInstructor(new Instructor());
        subject.setStudents(new HashSet<>());

        when(subjectRepository.findById(1)).thenReturn(Optional.of(subject));
    }

    @Test
    public void addSubject_OK() throws Exception{
        SubjectInputDto subjectInputDto = new SubjectInputDto();
        subjectInputDto.setSubjectName("SubjectName1");
        subjectInputDto.setComment("Comment1");
        subjectInputDto.setFinishDate(LocalDate.parse("2022-12-01"));
        subjectInputDto.setInitialDate(LocalDate.parse("2020-01-01"));

        Subject newSubject = new Subject(subjectInputDto);
        SubjectOutputDto newSubjectOutput = new SubjectOutputDto(newSubject);


        when(subjectController.addSubject(any(SubjectInputDto.class))).thenReturn(newSubjectOutput);

        mockMvc.perform(post("/subject")
                .content(om.writeValueAsString(newSubjectOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subjectName", is("SubjectName1")))
                .andExpect(jsonPath("$.comment", is("Comment1")))
                .andExpect(jsonPath("$.finishDate", is(LocalDate.parse("2022-12-01").format(formatter))))
                .andExpect(jsonPath("$.initialDate", is(LocalDate.parse("2020-01-01").format(formatter))));

        verify(subjectController, times(1)).addSubject(any(SubjectInputDto.class));
    }

    @Test
    public void updateSubject_OK() throws Exception{
        Subject updateSubject = new Subject();
        updateSubject.setSubjectId(1);
        updateSubject.setSubjectName("SubjectName1");
        updateSubject.setComment("Comment1");
        updateSubject.setFinishDate(LocalDate.parse("2022-12-01"));
        updateSubject.setInitialDate(LocalDate.parse("2020-01-01"));

        SubjectOutputDto updatedSubjectOutput = new SubjectOutputDto(updateSubject);

        when(subjectController.updateSubject(anyInt(), any(SubjectInputDto.class))).thenReturn(updatedSubjectOutput);

        mockMvc.perform(put("/subject/1")
                .content(om.writeValueAsString(updatedSubjectOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId", is(1)))
                .andExpect(jsonPath("$.subjectName", is("SubjectName1")))
                .andExpect(jsonPath("$.comment", is("Comment1")))
                .andExpect(jsonPath("$.finishDate", is(LocalDate.parse("2022-12-01").format(formatter))))
                .andExpect(jsonPath("$.initialDate", is(LocalDate.parse("2020-01-01").format(formatter))));

        verify(subjectController, times(1)).updateSubject(anyInt(), any(SubjectInputDto.class));
    }

    @Test
    public void getAllSubjects_OK() throws Exception{
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(
                new Subject(1, "SubjectName1", "Comment1", LocalDate.parse("2020-12-01"), LocalDate.parse("2022-01-01"), null, null));
        subjectList.add(
                new Subject(2, "SubjectName2", "Comment2", LocalDate.parse("2021-12-01"), LocalDate.parse("2023-01-01"), null, null));

        List<SubjectOutputDto> subjecOutputList = new ArrayList<>();
        for (Subject subject : subjectList){
            subjecOutputList.add(new SubjectOutputDto(subject));
        }

        when(subjectController.getAllSubjects(anyInt(), anyInt())).thenReturn(subjecOutputList);

        mockMvc.perform(get("/subject")
                .content(om.writeValueAsString(subjecOutputList))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].subjectId", is(1)))
                .andExpect(jsonPath("$[0].comment", is("Comment1")))
                .andExpect(jsonPath("$[0].finishDate", is(LocalDate.parse("2022-01-01").format(formatter))))
                .andExpect(jsonPath("$[1].subjectId", is(2)))
                .andExpect(jsonPath("$[1].comment", is("Comment2")))
                .andExpect(jsonPath("$[1].finishDate", is(LocalDate.parse("2023-01-01").format(formatter))));

        verify(subjectController, times(1)).getAllSubjects(anyInt(), anyInt());
    }

    @Test
    public void deleteSubjectById_OK() throws Exception {
        doNothing().when(subjectController).deleteSubjectById(1);

        mockMvc.perform(delete("/subject/1"))
                .andExpect(status().isOk());

        verify(subjectController, times(1)).deleteSubjectById(1);
    }
}
