package com.formacion.bosonit.block10docker.service;

import com.formacion.bosonit.block10docker.model.Student;
import com.formacion.bosonit.block10docker.model.dto.StudentInputDto;
import com.formacion.bosonit.block10docker.model.dto.StudentOutputDto;
import com.formacion.bosonit.block10docker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto){
        Student student = new Student();
        student.setName(studentInputDto.getName());
        student.setLastName(studentInputDto.getLastName());
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setBranch(studentInputDto.getBranch());
        studentRepository.save(student);
        return new StudentOutputDto(student);
    }

    @Override
    public List<StudentOutputDto> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        List<StudentOutputDto> studentOutputDtos = new ArrayList<>();
        for (Student student : students){
            studentOutputDtos.add(new StudentOutputDto(student));
        }
        return studentOutputDtos;
    }

}
