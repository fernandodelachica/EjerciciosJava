package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;

import java.util.List;

public interface StudentService {

    public StudentOutputDto addStudent(StudentInputDto studentInputDto);

    public List<StudentOutputDto>getAllStudent();
    public List<StudentOutputDto> getAllStudentsWithPerson(String type);

    public StudentOutputDto updateStudent(int id, StudentInputDto studentInputDto);

    public void deleteStudent(int id);

    public StudentFullOutputDto getStudentAndPersonById(int id);
}
