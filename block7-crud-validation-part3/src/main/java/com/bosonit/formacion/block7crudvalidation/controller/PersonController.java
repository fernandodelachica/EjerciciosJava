package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.ErrorResponse;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.service.PersonService;
import com.bosonit.formacion.block7crudvalidation.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto addPerson(@RequestBody PersonInputDto person) throws UnprocessableEntityException {
        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public PersonOutputDto getPersonById(@PathVariable int id) throws EntityNotFoundException{
        return personService.getPersonById(id);
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

    @GetMapping
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

}
