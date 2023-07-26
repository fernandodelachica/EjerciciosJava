package com.bosonit.formacion.block7CrudValidationRebuild.student.application;

import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentOutputDto;

import java.util.List;

public interface StudentService {

    StudentOutputDto addStudent(StudentInputDto studentInputDto);

    StudentOutputDto updateStudentById(int id, StudentInputDto studentInputDto);

    List<StudentOutputDto> getAllStudents(int pageSize, int pageNumber, String outputType);

    StudentOutputDto getStudentById(int id, String outputType);

    void deleteStudentById(int id);

    StudentOutputDto addSubjectToStudent(int studentId, List<Integer> subjectsIds);

    void deleteSubjectToStudent(int studentId, List<Integer> subjectsIds);

}
