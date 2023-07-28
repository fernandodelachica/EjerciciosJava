package com.bosonit.formacion.block7CrudValidationRebuild.person.controller;

import com.bosonit.formacion.block7CrudValidationRebuild.config.feign.InstructorFeign;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.ErrorResponse;
import com.bosonit.formacion.block7CrudValidationRebuild.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.application.PersonService;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    InstructorFeign instructorFeign;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/hola")
    @PreAuthorize("hasRole('ADMIN')")
    public String hola(){
        return "hola eres admin";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto addPerson(@RequestBody PersonInputDto personInputDto){
        personInputDto.setPassword(passwordEncoder.encode(personInputDto.getPassword()));
        return personService.addPerson(personInputDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PersonOutputDto updatePersonById(@PathVariable int id, @RequestBody PersonInputDto personInputDto){
        return personService.updatePersonById(id, personInputDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PersonOutputDto> getAllPersons(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return personService.getAllPersons(pageSize, pageNumber, outputType);
    }

    @GetMapping("/{personUser}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PersonOutputDto> getAllPersonsByUser(@PathVariable String personUser){
        return personService.getAllPersonsByUser(personUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePersonById(@PathVariable int id){
        personService.deletePersonById(id);
    }



    //RestTemplate 8081
    @GetMapping("/instructor/rest/{id}")
    public InstructorOutputDto getInstructor(@PathVariable int id){
        return personService.getInstructor(id);
    }

    //Feign 8081
    @GetMapping("/instructor/feign/{id}")
    public InstructorOutputDto getInstructorFeign(@PathVariable int id){
        return instructorFeign.getInstructor(id);
    }

    //CrossOrigin
    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/addperson")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto addPersonCross(@RequestBody PersonInputDto personInputDto){
        return personService.addPerson(personInputDto);
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/getAll")
    public Iterable<PersonOutputDto> getAllCross(
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "simple") String outputType){
        return personService.getAllPersons(pageSize, pageNumber, outputType);
    }

    //Criteria API
    @GetMapping("/customQuery")
    public Iterable<PersonOutputDto> findByNameSurnameAndCreatedDate(
            @RequestParam(required = false) String personUser,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate createdDate,
            @RequestParam(required = false) String dateCondition,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){

        HashMap<String, Object> conditions = new HashMap<>();
        if(personUser != null) conditions.put("personUser", personUser);
        if(name != null) conditions.put("name", name);
        if(surname != null) conditions.put("surname", name);
        if(createdDate != null && dateCondition != null){
            conditions.put("createdDate", createdDate);
            conditions.put("dateCondition", dateCondition);
        }
        if(orderBy != null) conditions.put("orderBy", orderBy);

        return personRepository.getPersonQuery(conditions, pageNumber, pageSize);
    }


    //Excepciones Handlers
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
