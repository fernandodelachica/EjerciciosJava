package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.model.dto.StudentInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.SubjectRepository;
import com.bosonit.formacion.block7crudvalidation.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectOutputDto addSubject(@RequestBody SubjectInputDto subjectInputDto){
        return subjectService.addSubject(subjectInputDto);
    }

    @GetMapping
    public List<SubjectOutputDto> getAllSubjects(){
        return subjectService.getAllSubjects();
    }


    @GetMapping("/estudiante/{id}")
    public List<SubjectOutputDto> getSubjectStudentById(@PathVariable int id){
        return subjectService.getSubjectStudentById(id);
    }

    @PutMapping("/{id}")
    public SubjectOutputDto updateSubject(@PathVariable int id, @RequestBody SubjectInputDto subjectInputDto){
        return subjectService.updateSubject(id, subjectInputDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable int id){
        subjectService.deleteSubject(id);
    }

}
