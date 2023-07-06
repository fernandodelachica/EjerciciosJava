package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.model.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.service.StudentService;
import com.bosonit.formacion.block7crudvalidation.service.StudentServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<StudentOutputDto>getAllStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/{type}")
    public List<StudentOutputDto> getAllStudentsWithPerson(@PathVariable String type){
        return studentService.getAllStudentsWithPerson(type);
    }

    @GetMapping("/estudiante/{id}")
    public StudentFullOutputDto getStudentAndPersonById(@PathVariable int id){
        return studentService.getStudentAndPersonById(id);
    }

    @PutMapping("/{id}")
    public StudentOutputDto updateStudent(@PathVariable int id, @RequestBody StudentInputDto studentInputDto){
        return studentService.updateStudent(id, studentInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }
}
