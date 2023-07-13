package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {

    SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);

    List<SubjectOutputDto> getAllSubjects();

    SubjectOutputDto getSubjectById(int id);

    List<SubjectOutputDto> getSubjectStudentById(int studentId);

    SubjectOutputDto updateSubject(int id, SubjectInputDto subjectInputDto);

    void deleteSubject(int id);
}
