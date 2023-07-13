package com.formacion.bosonit.block10docker.controller;

import com.formacion.bosonit.block10docker.model.dto.StudentInputDto;
import com.formacion.bosonit.block10docker.model.dto.StudentOutputDto;
import com.formacion.bosonit.block10docker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDto addStudent(@RequestBody StudentInputDto studentInputDto){
        return studentService.addStudent(studentInputDto);
    }

    @GetMapping
    public List<StudentOutputDto> getAllStudent(){
        return studentService.getAllStudent();
    }
}
