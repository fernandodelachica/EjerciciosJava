package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {

    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);
}
