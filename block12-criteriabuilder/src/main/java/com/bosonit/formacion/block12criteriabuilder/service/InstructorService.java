package com.bosonit.formacion.block12criteriabuilder.service;

import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorOutputDto;

import java.util.List;

public interface InstructorService {

    public InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto);

    public List<InstructorOutputDto> getAllInstructors();

    public InstructorOutputDto updateInstructor(int id, InstructorInputDto instructorInputDto);

    public void deleteInstructor(int id);

    InstructorOutputDto getInstructorById(int id, String outputType);
}
