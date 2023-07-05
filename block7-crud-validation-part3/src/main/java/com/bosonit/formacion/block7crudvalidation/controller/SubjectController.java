package com.bosonit.formacion.block7crudvalidation.controller;

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
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto){
        return subjectService.addSubject(subjectInputDto);
    }

    @GetMapping
    public List<SubjectOutputDto> getAllSubject(){
        return subjectService.getAllSubjects();
    }
}
