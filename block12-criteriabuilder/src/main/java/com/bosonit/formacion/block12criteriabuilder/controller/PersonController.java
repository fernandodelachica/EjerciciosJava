package com.bosonit.formacion.block12criteriabuilder.controller;

import com.bosonit.formacion.block12criteriabuilder.config.feign.InstructorFeign;
import com.bosonit.formacion.block12criteriabuilder.exception.EntityNotFoundException;
import com.bosonit.formacion.block12criteriabuilder.exception.ErrorResponse;
import com.bosonit.formacion.block12criteriabuilder.exception.UnprocessableEntityException;
import com.bosonit.formacion.block12criteriabuilder.model.dto.InstructorOutputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonInputDto;
import com.bosonit.formacion.block12criteriabuilder.model.dto.PersonOutputDto;
import com.bosonit.formacion.block12criteriabuilder.repository.PersonRepository;
import com.bosonit.formacion.block12criteriabuilder.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    InstructorFeign instructorFeign;

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto addPerson(@RequestBody PersonInputDto person){
        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public PersonOutputDto getPersonById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType){
        return personService.getPersonById(id, outputType);
    }

    @GetMapping
    public List<PersonOutputDto> getPersons(@RequestParam(defaultValue = "simple") String outputType){
        return personService.getPersons(outputType);
    }

    @PutMapping("/{id}")
    public PersonOutputDto updatePerson(@PathVariable int id, @RequestBody PersonInputDto updatedPerson){
        return personService.updatePerson(id, updatedPerson);
    }

    @GetMapping("/usuario/{personUser}")
    public ResponseEntity<?> findPersonByUser(@PathVariable String personUser){
        //Utilizamos una List, porque cabe la posibilidad de que haya 2 personas con un mismo usuario
        List<PersonOutputDto> personsByUserList = personService.getAllPersonByUser(personUser);
        if(personsByUserList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EL usuario "+personUser+" no existe");
        } else {
            return ResponseEntity.ok(personsByUserList);
        }
    }

    @GetMapping("/all")
    public List<PersonOutputDto> getAllPerson(){
        return personService.getAllPerson();
    }

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

    //RestTemplate 8081
    @GetMapping("/profesor/rest/{id}")
    public InstructorOutputDto getInstructor(@PathVariable int id){
        return personService.getInstructor(id);
    }

    //Feign 8081
    @GetMapping("/profesor/{id}")
    public InstructorOutputDto getInstructorFeign(@PathVariable int id){
        return instructorFeign.getTeacher(id);
    }


    //Criteria API
    @GetMapping("/customquery")
    public Iterable<PersonOutputDto> findByNameSurnameAndCreatedDate(
            @RequestParam(required = false) String personUser,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate createdDate,
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

}
