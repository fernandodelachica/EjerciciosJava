package com.bosonit.formacion.block7CrudValidationRebuild.student.controller;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.ErrorResponse;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.student.application.StudentService;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.dto.StudentOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDto addStudent(@Valid @RequestBody StudentInputDto studentInputDto){
        return studentService.addStudent(studentInputDto);
    }

    @PutMapping("/{id}")
    public StudentOutputDto updateStudent(@PathVariable int id, @RequestBody StudentInputDto studentInputDto){
        return studentService.updateStudentById(id, studentInputDto);
    }

    @PutMapping("/{id}/addSubject")
    public StudentOutputDto addSubjectToStudent(@PathVariable int id, @RequestBody Set<Integer> subjectsIds){
        return studentService.addSubjectToStudent(id, subjectsIds);
    }

    @GetMapping
    public List<StudentOutputDto> getAllStudents(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return studentService.getAllStudents(pageSize, pageNumber, outputType);
    }

    @GetMapping("/{id}")
    public StudentOutputDto getStudentById(
            @PathVariable int id,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return studentService.getStudentById(id, outputType);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
    }

    @DeleteMapping("/{id}/deleteSubject")
    public void deleteSubjectToStudent(
            @PathVariable int id,
            @RequestBody Set<Integer> subjectsIds){
        studentService.deleteSubjectToStudent(id, subjectsIds);
    }


    //Exceptions Handlers
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimeStamp(new Date());
        response.setHttpCode(404);
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handlerUnprocessableEntityException(UnprocessableEntityException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimeStamp(new Date());
        response.setHttpCode(422);
        return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
