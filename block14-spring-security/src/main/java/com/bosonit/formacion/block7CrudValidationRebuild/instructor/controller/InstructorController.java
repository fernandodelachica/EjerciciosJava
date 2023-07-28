package com.bosonit.formacion.block7CrudValidationRebuild.instructor.controller;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.ErrorResponse;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.application.InstructorService;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorOutputDto addInstructor(@Valid @RequestBody InstructorInputDto instructorInputDto){
        return instructorService.addInstructor(instructorInputDto);
    }

    @PutMapping("/{id}")
    public InstructorOutputDto updateInstructorById(@PathVariable int id, @Valid @RequestBody InstructorInputDto instructorInputDto){
        return instructorService.updateInstructorById(id, instructorInputDto);
    }

    @GetMapping
    public List<InstructorOutputDto> getAllInstructors(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return instructorService.getAllInstructors(pageSize, pageNumber, outputType);
    }

    @GetMapping("/{id}")
    public InstructorOutputDto getInstructorById(
            @PathVariable int id,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return instructorService.getInstructorById(id, outputType);
    }

    @DeleteMapping("/{id}")
    public void deleteInstructorById(@PathVariable int id){
        instructorService.deleteInstructorById(id);
    }


    //Exception Handlers
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
