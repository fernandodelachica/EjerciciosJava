package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorOutputDto;

import java.util.List;

public interface InstructorService {

    public InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto);

    public List<InstructorOutputDto> getAllInstructors();

    public InstructorOutputDto updateInstructor(int id, InstructorInputDto instructorInputDto);

    public void deleteInstructor(int id);

    InstructorOutputDto getInstructorById(int id, String outputType);
}
