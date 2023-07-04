package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorOutputDto;
import com.bosonit.formacion.block7crudvalidation.service.InstructorService;
import com.bosonit.formacion.block7crudvalidation.service.InstructorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorOutputDto addInstructor(@RequestBody InstructorInputDto instructorInputDto){
        return instructorService.addInstructor(instructorInputDto);
    }

    //READ
    @GetMapping
    public List<InstructorOutputDto> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    //UPDATE
    @PutMapping("/{id}")
    public InstructorOutputDto updateInstructor(@PathVariable int id, @RequestBody InstructorInputDto instructorInputDto){
        return  instructorService.updateInstructor(id, instructorInputDto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable int id){
        instructorService.deleteInstructor(id);
    }
}
