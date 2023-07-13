package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
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


        Subject subject = new Subject();
        subject.setSubjectName(subjectInputDto.getSubjectName());
        subject.setComment(subjectInputDto.getComment());
        subject.setInitialDate(subjectInputDto.getInitialDate());
        subject.setFinishDate(subjectInputDto.getFinishDate());
        subject.setInstructor(instructor);
        subject.setStudents(iterateStudent(subjectInputDto));

        Subject savedSubject = subjectRepository.save(subject);
        return new SubjectOutputDto(savedSubject);
    }

    @Override
    public List<SubjectOutputDto> getAllSubjects(){
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectOutputDto> subjectOutputDtos = new ArrayList<>();

        for (Subject subject : subjects){
            subjectOutputDtos.add(new SubjectOutputDto(subject));
        }
        return subjectOutputDtos;
    }

    @Override
    public SubjectOutputDto getSubjectById(int id){
        return new SubjectOutputDto(subjectRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La asignatura con el id "+id+" no se ha encontrado")));
    }

    @Override
    public List<SubjectOutputDto> getSubjectStudentById(int studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El Estudiante con el id "+studentId+" no se ha encontrado."));
        return student.getSubjects().stream().map(SubjectOutputDto::new).toList();
    }

    @Override
    public SubjectOutputDto updateSubject(int id, SubjectInputDto subjectInputDto){
        Subject updatedSubject = subjectRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La asignatura con el id "+id+" no se ha encontrado"));

        //Comprobación del campo instructorId
        if (subjectInputDto.getInstructorId() != null){
            Instructor updatedInstructor = instructorRepository.findById(subjectInputDto.getInstructorId()).orElseThrow(()->
                    new EntityNotFoundException("El profesor con el id "+subjectInputDto.getInstructorId()+" no se ha encontrado"));

            updatedSubject.setInstructor(updatedInstructor);
        }
        //Comprobación de la lista students
        if (!subjectInputDto.getStudents().isEmpty()){
            updatedSubject.setStudents(iterateStudent(subjectInputDto));
        }
        checkSubject(subjectInputDto, updatedSubject);

        subjectRepository.save(updatedSubject);
        return new SubjectOutputDto(updatedSubject);

    }

    public void deleteSubject(int id){
        subjectRepository.deleteById(id);
    }

    private Set<Student> iterateStudent(SubjectInputDto subjectInputDto) {
        Set<Student> students = new HashSet<>();
        for (Integer studentId : subjectInputDto.getStudents()){
            Student student = studentRepository.findById(studentId).orElseThrow(()->
                    new EntityNotFoundException("El id del estudiante "+studentId+" no existe"));
            if (student == null){
                throw new UnprocessableEntityException("El campo estudiante es nulo");
            }
            students.add(student);
        }
        return students;
    }



    private static void checkSubject(SubjectInputDto subjectInputDto, Subject updatedSubject) {
        if (subjectInputDto.getComment() != null){
            updatedSubject.setComment(subjectInputDto.getComment());
        }
        if (subjectInputDto.getSubjectName() != null){
            updatedSubject.setSubjectName(subjectInputDto.getSubjectName());
        }
        if (subjectInputDto.getInitialDate() != null){
            updatedSubject.setInitialDate(subjectInputDto.getInitialDate());
        }
        if (subjectInputDto.getFinishDate() != null){
            updatedSubject.setFinishDate(subjectInputDto.getFinishDate());
        }
    }

}
