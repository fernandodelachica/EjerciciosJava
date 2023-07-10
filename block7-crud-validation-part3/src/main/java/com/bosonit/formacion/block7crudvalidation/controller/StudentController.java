package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.model.dto.StudentFullOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.service.StudentService;
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
    public List<StudentOutputDto>getAllStudent(@RequestParam(defaultValue = "simple") String outputType){
        return studentService.getAllStudent(outputType);
    }
    @GetMapping("/full")
    public List<StudentOutputDto> getAllStudentsWithPerson(){
        return studentService.getAllStudentsWithPerson();
    }

    @GetMapping("/{id}")
    public StudentOutputDto getStudentByID(@PathVariable int id, @RequestParam(defaultValue = "simple") String ouputType){
        return studentService.getStudentById(id, ouputType);
    }

    @GetMapping("/full/{id}")
    public StudentFullOutputDto getStudentFullById(@PathVariable int id){
        return studentService.getStudentFullById(id);
    }

    @PutMapping("/{id}")
    public StudentOutputDto updateStudent(@PathVariable int id, @RequestBody StudentInputDto studentInputDto){
        return studentService.updateStudent(id, studentInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }


    @PutMapping("/subjectsAdd/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDto addSubjectstoStudent(@PathVariable int id, @RequestBody List<Integer> subjectIdsInput){
        return studentService.addSubjectstoStudent(id, subjectIdsInput);
    }

    @PutMapping("/asignaturaDelete/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public StudentOutputDto deleteSubjectToStudent(@PathVariable int id, @RequestBody List<Integer> subjectIdsInput){
        return studentService.deleteSubjectToStudent(id, subjectIdsInput);
    }
}
