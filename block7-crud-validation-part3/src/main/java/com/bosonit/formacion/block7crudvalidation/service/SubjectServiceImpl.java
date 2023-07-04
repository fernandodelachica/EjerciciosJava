package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.InstructorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import com.bosonit.formacion.block7crudvalidation.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto){
        Subject subject =new Subject();
        List<Student> students = studentRepository.findAllById(subjectInputDto.getStudentsIds());

        if (students.isEmpty()){
            throw new EntityNotFoundException("Los ids no existen");
        }
        subject.setTopic(subjectInputDto.getTopic());
        subject.setComment(subjectInputDto.getComment());
        subject.setStudents(students);
        subject.setInitialDate(subjectInputDto.getInitialDate());
        subject.setFinishDate(subjectInputDto.getFinishDate());
        subjectRepository.save(subject);
        return new SubjectOutputDto(subject);

    }
}
