package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Person;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.InstructorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto){
        Student student = new Student();
        
        //Busca si la id de la persona y del instructor está en la base de datos y los asigna una variable
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
    public List<StudentOutputDto>getAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentOutputDto> studentFinalOutput = new ArrayList<>();
        for (Student student : studentList){
            studentFinalOutput.add(new StudentOutputDto(student));
        }
        return studentFinalOutput;
    }
    @Override
    public List<StudentOutputDto> getAllStudentsWithPerson(String type){
        List<Student> studentList = studentRepository.findAll();
        List<StudentOutputDto> studentFinalOutput = new ArrayList<>();

        for(Student student : studentList) {
            if (type.equals("full")) {
                studentFinalOutput.add(new StudentFullOutputDto(student));
            } else studentFinalOutput.add(new StudentOutputDto(student));
        }
        return studentFinalOutput;
    }

    @Override
    public StudentFullOutputDto getStudentAndPersonById(int id){
        Student studentAndPerson = studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado"));
        return new StudentFullOutputDto(studentAndPerson);
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
