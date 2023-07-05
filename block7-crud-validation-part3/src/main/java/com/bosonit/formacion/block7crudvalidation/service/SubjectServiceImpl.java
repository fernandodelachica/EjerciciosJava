package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.InstructorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import com.bosonit.formacion.block7crudvalidation.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    /*
    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) {
        Subject subject = new Subject();
        List<Student> students = studentRepository.findAllById(subjectInputDto.getStudents());

        if (students.isEmpty()) {
            throw new EntityNotFoundException("Los ids no existen");
        }
        subject.setSubjectName(subjectInputDto.getSubjectName());
        subject.setComment(subjectInputDto.getComment());
        subject.setStudents(students);
        subject.setInitialDate(subjectInputDto.getInitialDate());
        subject.setFinishDate(subjectInputDto.getFinishDate());
        subjectRepository.save(subject);
        return new SubjectOutputDto(subject);
    }*/


    @Override
    public List<SubjectOutputDto> getAllSubjects(){
        return subjectRepository.findAll().stream().map(Subject::subjectToSubjectOutputDto).toList();

        }
    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto) {

        Subject subject = new Subject(subjectInputDto);
        List<Student> students = studentRepository.findAllById(subjectInputDto.getStudents());

        subject.setStudents(students);
        return subjectRepository.save(subject).subjectToSubjectOutputDto();
    }
}
