package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Person;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
import com.bosonit.formacion.block7crudvalidation.model.dto.*;
import com.bosonit.formacion.block7crudvalidation.repository.InstructorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import com.bosonit.formacion.block7crudvalidation.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto){
        Student student = new Student();
        
        //Busca si el id de la persona y del instructor está en la base de datos y los asigna una variable
        Person personFound = personRepository.findById(studentInputDto.getPersonId()).orElseThrow(()->
                new EntityNotFoundException("La persona con el id "+studentInputDto.getPersonId()+" no existe"));
        Instructor instructorFound = instructorRepository.findById(studentInputDto.getInstructorId()).orElseThrow(()->
                new EntityNotFoundException("El profesor con el id "+studentInputDto.getInstructorId()+" no existe."));
        
        //Comprueba si la id ya está asignada a un estudiante o a un profesor
        if (studentRepository.existsById(studentInputDto.getPersonId())){
            new UnprocessableEntityException("Esta id ya está asignada a un alumno");
        }
        if (instructorRepository.existsById(studentInputDto.getPersonId())){
            new UnprocessableEntityException("Esta id está asignada a un profesor");
        }
        checkStudent(studentInputDto);
        student.setPerson(personFound);
        student.setInstructor(instructorFound);
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());
        studentRepository.save(student);
        return new StudentOutputDto(student);
    }

    @Override
    public StudentOutputDto addSubjectstoStudent(int studentId, List<Integer> subjectsIdsInput){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+studentId+" no se ha encotrado"));
        for (Integer id : subjectsIdsInput){
            Subject subject = subjectRepository.findById(id).orElseThrow(()->
                    new EntityNotFoundException("La asignatura con el id "+id+" no se ha encontrado"));

            if(!student.getSubjects().contains(subject)){
                subject.getStudents().add(student);
                student.getSubjects().add(subject);
            }
        }
        studentRepository.save(student);
        return new StudentOutputDto(student);
    }

    @Override
    public List<StudentOutputDto>getAllStudent(String outputType){
        List<Student> studentList = studentRepository.findAll();
        List<StudentOutputDto> studentFinalOutput = new ArrayList<>();
        for (Student student : studentList){
            if(outputType.equals("full")){
                studentFinalOutput.add(new StudentFullOutputDto(student));
            } else studentFinalOutput.add(new StudentOutputDto(student));
        }
        return studentFinalOutput;
    }
    @Override
    public List<StudentOutputDto> getAllStudentsWithPerson(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentOutputDto> studentFinalOutput = new ArrayList<>();

        for(Student student : studentList) {
            studentFinalOutput.add(new StudentFullOutputDto(student));
        }
        return studentFinalOutput;
    }

    @Override
    public StudentFullOutputDto getStudentFullById(int id){
        Student studentAndPerson = studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado"));
        return new StudentFullOutputDto(studentAndPerson);
    }
    @Override
    public StudentOutputDto getStudentById(int id, String outputType){
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La estudiante con el id: "+id+" no existe"));
        if(outputType.equals("full")){
            return new StudentOutputDto(student);
        }else return new StudentFullOutputDto(student);
    }
    @Override
    public StudentOutputDto updateStudent(int id, StudentInputDto studentInputDto){
        Student updatedStudent = studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado"));
        checkStudent(studentInputDto);

        updatedStudent.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        updatedStudent.setComments(studentInputDto.getComments());
        updatedStudent.setBranch(studentInputDto.getBranch());
        studentRepository.save(updatedStudent);
        return new StudentOutputDto(updatedStudent);
    }

    @Override
    public void deleteStudent(int id){
        studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado."));
        studentRepository.deleteById(id);
    }

    public StudentOutputDto deleteSubjectToStudent(int studentId, List<Integer> subjectsIdsInput){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+studentId+" no se ha encotrado"));
        for (Integer id : subjectsIdsInput) {
            Subject subject = subjectRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("La asignatura con el id " + id + " no se ha encontrado"));
            if (student.getSubjects().contains(subject)) {
                student.getSubjects().remove(subject);
            }
            studentRepository.save(student);
        }
        return new StudentOutputDto(student);
    }

    private static void checkStudent(StudentInputDto studentInputDto) {
        //Comprobadores de que no sean null
        if (studentInputDto.getNumHoursWeek() == null){
            throw new UnprocessableEntityException("El campo NumHoursWeek es nulo");
        }
        if (studentInputDto.getBranch() == null){
            throw new UnprocessableEntityException("El campo Branch es nulo");
        }
    }
}
