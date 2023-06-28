package com.bosonit.formacion.block7crudvalidation.controller;

import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) throws Exception {
        return ResponseEntity.ok().body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona con el id "+id+" no encontrada");
        }
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
}
