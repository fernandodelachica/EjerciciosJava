package com.bosonit.formacion.block12criteriabuilder.service;

import com.bosonit.formacion.block12criteriabuilder.model.dto.StudentFullOutputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.StudentInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.StudentOutputDto;

import java.util.List;

public interface StudentService {

    StudentOutputDto addStudent(StudentInputDto studentInputDto);

    List<StudentOutputDto>getAllStudent(String outputType);
    List<StudentOutputDto> getAllStudentsWithPerson();

    StudentOutputDto updateStudent(int id, StudentInputDto studentInputDto);

    void deleteStudent(int id);

    StudentFullOutputDto getStudentFullById(int id);

    StudentOutputDto getStudentById(int id, String outputType);

    StudentOutputDto addSubjectstoStudent(int studentId, List<Integer> subjectsIdsInput);

    StudentOutputDto deleteSubjectToStudent(int studentId, List<Integer> subjectsIdsInput);
}
