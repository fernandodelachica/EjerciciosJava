package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;

public interface SubjectService {
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);
}
