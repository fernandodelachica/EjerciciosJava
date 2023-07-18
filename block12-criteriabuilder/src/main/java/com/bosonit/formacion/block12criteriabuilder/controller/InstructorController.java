package com.bosonit.formacion.block12criteriabuilder.controller;

import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorOutputDto;
import com.bosonit.formacion.block12criteriabuilder.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public InstructorOutputDto getInstructorById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType){
        return instructorService.getInstructorById(id, outputType);
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
