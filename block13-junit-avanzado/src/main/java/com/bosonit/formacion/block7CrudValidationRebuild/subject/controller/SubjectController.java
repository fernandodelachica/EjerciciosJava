package com.bosonit.formacion.block7CrudValidationRebuild.subject.controller;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.ErrorResponse;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.application.SubjectService;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.repository.SubjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectOutputDto addSubject(@Valid @RequestBody SubjectInputDto subjectInputDto){
        return subjectService.addSubject(subjectInputDto);
    }

    @PutMapping("/{id}")
    public SubjectOutputDto updateSubject(@PathVariable int id, @Valid @RequestBody SubjectInputDto subjectInputDto){
        return subjectService.updateSubjectById(id, subjectInputDto);
    }

    @GetMapping
    public List<SubjectOutputDto> getAllSubjects(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNumber){
        return subjectService.getAllSubjects(pageSize, pageNumber);
    }

    @GetMapping("/{id}")
    public SubjectOutputDto getSubjectById(@PathVariable int id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/student/{id}")
    public List<SubjectOutputDto> getSubjectStudentById(@PathVariable int id){
        return subjectService.getSubjectStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectById(@PathVariable int id){
        subjectService.deleteSubjectById(id);
    }


    //Exceptions Handlers
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimeStamp(new Date());
        response.setHttpCode(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handlerUnprocessableEntityException(UnprocessableEntityException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimeStamp(new Date());
        response.setHttpCode(422);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
