package com.bosonit.formacion.block7CrudValidationRebuild.mockitotest;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.student.application.StudentService;
import com.bosonit.formacion.block7CrudValidationRebuild.student.controller.StudentController;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentOutputDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

    @InjectMocks
    StudentController studentController;

    @Mock
    StudentService studentService;

    @Test
    public void addStudent_OK(){
        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setComments("Comments1");
        studentInputDto.setBranch("Branch1");
        studentInputDto.setPersonId(1);
        studentInputDto.setInstructorId(1);
        studentInputDto.setNumHoursWeek(40);

        Student newStudent = new Student(studentInputDto);

        Mockito.when(studentService.addStudent(any(StudentInputDto.class))).thenReturn(new StudentOutputDto(newStudent));

        StudentOutputDto response = studentController.addStudent(studentInputDto);

        assertEquals("Comments1", response.getComments());
        assertEquals("Branch1", response.getBranch());
        assertEquals(Integer.valueOf(1), response.getPersonId());
        assertEquals(Integer.valueOf(1), response.getInstructorId());
        assertEquals(Integer.valueOf(40), response.getNumHoursWeek());
    }

    @Test
    public void updateStudent_OK() {
        Student student = new Student();
        student.setStudentId(1);
        student.setComments("Comments1");
        student.setBranch("Branch1");
        student.setNumHoursWeek(40);
        student.setPerson(
                new Person(1, "Usuario1", "pass123", "Nombre1", "surname1", "company1@email.com", "personal1@email.com", "Ciudad1",
                        false, parse("2012-12-03T23:00:00.000+00:00"), "urlImage1.png", parse("2023-04-01T23:00:00.000+00:00"), null, null));
        student.setInstructor(
                new Instructor(1, "Comments1", "Branch1", null, null));

        StudentInputDto studentInputDto = new StudentInputDto();
        studentInputDto.setNumHoursWeek(36);

        Mockito.when(studentService.updateStudentById(anyInt(), any(StudentInputDto.class))).thenAnswer(invocation -> {
            student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
            return new StudentOutputDto(student);
        });

        StudentOutputDto response = studentController.updateStudent(student.getStudentId(), studentInputDto);

        assertEquals(Integer.valueOf(1), response.getStudentId());
        assertEquals("Comments1", response.getComments());
        assertEquals("Branch1", response.getBranch());
        assertEquals(Integer.valueOf(36), response.getNumHoursWeek());
        assertEquals(Integer.valueOf(1), response.getPersonId());
        assertEquals(Integer.valueOf(1), response.getInstructorId());
    }

    @Test
    public void getAllStudents_OK(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, 40,"Comments1","Branch1",null,null,null));
        studentList.add(new Student(2, 36,"Comments2","Branch2",null,null,null));


        List<StudentOutputDto> studentOutputList = new ArrayList<>();
        for(Student student : studentList){
            studentOutputList.add(new StudentOutputDto(student));
        }

        //Simulamos el servicio y con el thenReturn le decimos qué output sale
        Mockito.when(studentService.getAllStudents(anyInt(), anyInt(), anyString())).thenReturn(studentOutputList);

        //Llamamos al servicio y comprobamos que devuelve los valores correctos
        List<StudentOutputDto> response = studentController.getAllStudents(anyInt(), anyInt(), anyString());

        assertEquals(Integer.valueOf(1), response.get(0).getStudentId());
        assertEquals(Integer.valueOf(40), response.get(0).getNumHoursWeek());
        assertEquals("Comments1", response.get(0).getComments());
        assertEquals("Branch1", response.get(0).getBranch());
        assertEquals(Integer.valueOf(2), response.get(1).getStudentId());
        assertEquals(Integer.valueOf(36), response.get(1).getNumHoursWeek());
        assertEquals("Comments2", response.get(1).getComments());
        assertEquals("Branch2", response.get(1).getBranch());
    }

    @Test
    public void deleteStudentById_OK(){

        doNothing().when(studentService).deleteStudentById(1);

        studentController.deleteStudent(1);

        verify(studentService, times(1)).deleteStudentById(1);

    }
}
