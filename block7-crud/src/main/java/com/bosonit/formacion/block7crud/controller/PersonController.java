package com.bosonit.formacion.block7crud.controller;

import com.bosonit.formacion.block7crud.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crud.model.dto.PersonOutputDto;
import com.bosonit.formacion.block7crud.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person){
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personService.addPerson(person));
    }

    /*Actualizar datos de Person
     *   1. Utiliza el servicio del filtro getPersonById para obtener la Persona pasada por el ID del parámetro
     *   2. Utiliza el servicio updatePerson programada en ServiceImpl con los parámetros tanto del @PathVariable como del Body
     *   3. En el caso de que NO encuentre el ID de Person devuelve un 404 Not found
     * */
    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePersonById(@PathVariable int id, @RequestBody PersonInputDto updatedPerson){
        try{
            personService.getPersonById(id);
            return ResponseEntity.ok().body(personService.updatePerson(id, updatedPerson));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable int id){
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("El usuario con el id: "+id+" ha sido eliminado");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    /*Con ResponseEntity<?> indicamos que el cuerpo de la respuesta puede ser de cualquier tipo
    * - En el caso de 200 OK nos devuelve una respuesta de tipo person
    * ----Busca la persona por el ID y lo muestra
    * - En el caso de 404 NOT_FOUND devuelve un String
    * */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona con el id "+id+" no encontrada");
        }
    }
    //Get que busca los usuarios por nombre a través del servicio getAllPersonByNameLike que está alojado en PersonServiceImpl
    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> findPersonByNameLike(@PathVariable String name){
        List<PersonOutputDto> personByNameList = personService.getAllPersonByNameLike(name);
        if (personByNameList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningún usuario con el nombre: "+name);
        } else{
            return ResponseEntity.ok(personByNameList);
        }
    }

    //Devuelve todos los datos introducidos
    @GetMapping
    public List<PersonOutputDto> getAllPerson(){
        return personService.getAllPerson();
    }
}
