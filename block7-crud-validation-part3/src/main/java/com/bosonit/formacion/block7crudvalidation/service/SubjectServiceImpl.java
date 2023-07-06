package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
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

import java.util.*;

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

        //Relación OneToMany
        Instructor instructor = instructorRepository.findById(subjectInputDto.getInstructorId()).orElseThrow(()->
                new EntityNotFoundException("El id del profesor "+subjectInputDto.getInstructorId()+" no existe."));
        if (instructor == null){
            throw new UnprocessableEntityException("El campo profesor es nulo");
        }

        //Relación ManyToMany
        Set<Student> students = new HashSet<>();
        for (Integer studentId : subjectInputDto.getStudents()){
            Student student = studentRepository.findById(studentId).orElseThrow(()->
                    new EntityNotFoundException("El id del estudiante "+studentId+" no existe"));
            if (student == null){
                throw new UnprocessableEntityException("El campo estudiante es nulo");
            }
            students.add(student);
        }
        Subject subject = new Subject();
        subject.setSubjectName(subjectInputDto.getSubjectName());
        subject.setComment(subjectInputDto.getComment());
        subject.setInitialDate(subjectInputDto.getInitialDate());
        subject.setFinishDate(subjectInputDto.getFinishDate());
        subject.setInstructor(instructor);
        subject.setStudents(students);

        Subject savedSubject = subjectRepository.save(subject);
        return new SubjectOutputDto(savedSubject);
    }

}
