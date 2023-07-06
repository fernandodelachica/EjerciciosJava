package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {

    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);

    public List<SubjectOutputDto> getAllSubjects();

    public SubjectOutputDto getSubjectById(int id);

    public List<SubjectOutputDto> getSubjectStudentById(int studentId);

    public SubjectOutputDto updateSubject(int id, SubjectInputDto subjectInputDto);

    public void deleteSubject(int id);
}
