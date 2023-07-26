package com.bosonit.formacion.block7CrudValidationRebuild.subject.application;

import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectOutputDto;

import java.util.List;

public interface SubjectService {

    SubjectOutputDto addSubject(SubjectInputDto subjectInputDto);

    SubjectOutputDto updateSubjectById(int id, SubjectInputDto subjectInputDto);

    List<SubjectOutputDto> getAllSubjects(int pageSize, int pageNumber);

    SubjectOutputDto getSubjectById(int id);

    void deleteSubjectById(int id);

    List<SubjectOutputDto> getSubjectStudentById(int studentId);
}
