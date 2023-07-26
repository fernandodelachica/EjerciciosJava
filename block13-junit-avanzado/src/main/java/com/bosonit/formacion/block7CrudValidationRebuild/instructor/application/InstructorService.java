package com.bosonit.formacion.block7CrudValidationRebuild.instructor.application;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;

import java.util.List;

public interface InstructorService {
    InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto);

    InstructorOutputDto updateInstructorById(int id, InstructorInputDto instructorInputDto);

    List<InstructorOutputDto> getAllInstructors(int pageSize, int pageNumber, String outputType);

    InstructorOutputDto getInstructorById(int id, String outputType);

    void deleteInstructorById(int id);
}
