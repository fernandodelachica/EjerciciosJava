package com.bosonit.formacion.block7CrudValidationRebuild.student.application;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.repository.InstructorRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.repository.StudentRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    PersonRepository personRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto){
        Person personFound = personRepository.findById(studentInputDto.getPersonId()).orElseThrow(()->
                new RuntimeException("La persona con el id "+studentInputDto.getPersonId()+" no existe"));
        Instructor instructorFound = instructorRepository.findById(studentInputDto.getInstructorId()).orElseThrow(()->
                new RuntimeException("El profesor con el id "+studentInputDto.getInstructorId()+" no existe"));

        if(studentRepository.existsById(studentInputDto.getPersonId())){
            throw new UnprocessableEntityException("Esta id ya está asignada a un alumno");
        }
        if(instructorRepository.existsById(studentInputDto.getPersonId())){
            throw new UnprocessableEntityException("Esta id ya está asignada a un profesor");
        }
        Student newStudent = new Student(studentInputDto);

        newStudent.setPerson(personFound);
        newStudent.setInstructor(instructorFound);
        studentRepository.save(newStudent);

        return new StudentOutputDto(newStudent);
    }
    @Override
    public StudentOutputDto updateStudentById(int id, StudentInputDto studentInputDto){
        Student updatedStudent = studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado"));
        checkAndUpdateStudent(studentInputDto, updatedStudent);
        studentRepository.save(updatedStudent);
        return new StudentOutputDto(updatedStudent);
    }


    public void deleteStudentById(int id){
        studentRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+id+" no se ha encontrado."));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentOutputDto> getAllStudents(int pageSize, int pageNumber, String outputType){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Student> studentsPage = studentRepository.findAll(pageRequest);
        List<StudentOutputDto> studentFinalOutput = new ArrayList<>();
        for (Student student : studentsPage){
            if(outputType.equals("full")){
                studentFinalOutput.add(new StudentFullOutputDto(student));
            } else studentFinalOutput.add(new StudentOutputDto(student));
        }
        return studentFinalOutput;
    }

    @Override
    public StudentOutputDto getStudentById(int id, String outputType){
        Student studentFound = studentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("La estudiante con el id: "+id+" no existe"));
        if(outputType.equals("full")){
            return new StudentFullOutputDto(studentFound);
        } else return new StudentOutputDto(studentFound);
    }

    @Override
    public StudentOutputDto addSubjectToStudent(int studentId, List<Integer> subjectsIds){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+studentId+" no se ha encotrado"));
        for (Integer id : subjectsIds){
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
    public void deleteSubjectToStudent(int studentId, List<Integer> subjectsIds){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El estudiante con el id "+studentId+" no se ha encotrado"));
        for (Integer id : subjectsIds) {
            Subject subject = subjectRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("La asignatura con el id " + id + " no se ha encontrado"));
            if (student.getSubjects().contains(subject)) {
                student.getSubjects().remove(subject);
            }
            studentRepository.save(student);
        }
    }


    //Métodos
    private static Student checkAndUpdateStudent(StudentInputDto studentInputDto, Student updatedStudent) {
        if (studentInputDto.getNumHoursWeek() != null){
            updatedStudent.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        }
        if (studentInputDto.getComments() != null && !studentInputDto.getComments().equals("")){
            updatedStudent.setComments(studentInputDto.getComments());
        }
        if (studentInputDto.getBranch() != null && !studentInputDto.getBranch().equals("")){
            updatedStudent.setBranch(studentInputDto.getBranch());
        }
        return updatedStudent;
    }

}
