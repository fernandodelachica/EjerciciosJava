package com.formacion.bosonit.block10docker.service;


import com.formacion.bosonit.block10docker.model.dto.StudentInputDto;
import com.formacion.bosonit.block10docker.model.dto.StudentOutputDto;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto studentInputDto);

    List<StudentOutputDto> getAllStudent();
}
