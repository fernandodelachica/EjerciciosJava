package com.bosonit.formacion.block7CrudValidationRebuild.mockmvctest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.controller.StudentController;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.repository.StudentRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    StudentController studentController;

    @Before
    public void init(){
        Student student = new Student();
        student.setStudentId(1);
        student.setBranch("Branch1");
        student.setComments("Comments1");
        student.setNumHoursWeek(40);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
    }


    @Test
    public void addStudent_OK() throws Exception{

        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setBranch("Branch1");
        studentInputDto.setComments("Comments1");
        studentInputDto.setNumHoursWeek(40);
        studentInputDto.setPersonId(1);
        studentInputDto.setInstructorId(2);

        Student newStudent = new Student(studentInputDto);
        StudentOutputDto newStudentOutput = new StudentOutputDto(newStudent);

        when(studentController.addStudent(any(StudentInputDto.class))).thenReturn(newStudentOutput);

        mockMvc.perform(post("/student")
                .content(om.writeValueAsString(newStudentOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.branch", is("Branch1")))
                .andExpect(jsonPath("$.comments", is("Comments1")))
                .andExpect(jsonPath("$.numHoursWeek", is(40)))
                .andExpect(jsonPath("$.personId", is(1)))
                .andExpect(jsonPath("$.instructorId", is(2)));

        verify(studentController, times(1)).addStudent(any(StudentInputDto.class));

    }

    @Test
    public void updateStudent_OK() throws Exception {
        Student updateStudent = new Student();
        updateStudent.setStudentId(1);
        updateStudent.setBranch("Branch1");
        updateStudent.setComments("Comments1");
        updateStudent.setNumHoursWeek(40);
        updateStudent.setPerson(
                new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));

        updateStudent.setInstructor(
                new Instructor(2, "Comments1", "Branch1",
                        new Person(2, "Usuario2", "pass123", "Nombre2", "surname2", "company2@email.com", "personal2@email.com", "Ciudad2",
                                    false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null)));

        StudentOutputDto updateStudentOutput = new StudentOutputDto(updateStudent);

        when(studentController.updateStudent(anyInt(), any(StudentInputDto.class))).thenReturn(updateStudentOutput);

        mockMvc.perform(put("/student/1")
                .content(om.writeValueAsString(updateStudentOutput))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(1)))
                .andExpect(jsonPath("$.branch", is("Branch1")))
                .andExpect(jsonPath("$.comments", is("Comments1")))
                .andExpect(jsonPath("$.numHoursWeek", is(40)))
                .andExpect(jsonPath("$.personId", is(1)))
                .andExpect(jsonPath("$.instructorId", is(2)));

        verify(studentController, times(1)).updateStudent(anyInt(), any(StudentInputDto.class));
    }

    @Test
    public void getAllStudents_OK() throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(
                new Student(1, 40, "Comments1", "Branch1", new Person(), new Instructor(), new HashSet<>()));
        studentList.add(
                new Student(2, 36, "Comments2", "Branch2", new Person(), new Instructor(), new HashSet<>()));

        List<StudentOutputDto> studentOutputList = new ArrayList<>();
        for (Student student : studentList){
            studentOutputList.add(new StudentOutputDto(student));
        }

        when(studentController.getAllStudents(anyInt(), anyInt(), anyString())).thenReturn(studentOutputList);

        mockMvc.perform(get("/student")
                .content(om.writeValueAsString(studentOutputList))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].studentId", is(1)))
                .andExpect(jsonPath("$[0].numHoursWeek", is(40)))
                .andExpect(jsonPath("$[0].branch", is("Branch1")))
                .andExpect(jsonPath("$[1].studentId", is(2)))
                .andExpect(jsonPath("$[1].numHoursWeek", is(36)))
                .andExpect(jsonPath("$[1].branch", is("Branch2")));

        verify(studentController, times(1)).getAllStudents(anyInt(), anyInt(), anyString());
    }

    @Test
    public void deleteStudentById_OK() throws Exception {
        doNothing().when(studentController).deleteStudent(1);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());

        verify(studentController, times(1)).deleteStudent(1);
    }
}
