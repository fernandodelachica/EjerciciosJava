package com.bosonit.formacion.block6simplecontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class Controller {

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name){
        return "Hola " + name;
    }


    @PostMapping ("/useradd")
    public ResponseEntity<Person> jsonPerson(@RequestBody Person person){
        person.setAge(person.getAge()+1);
        return ResponseEntity.ok(person);
    }

}
