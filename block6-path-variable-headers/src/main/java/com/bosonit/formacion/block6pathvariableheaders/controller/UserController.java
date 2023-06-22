package com.bosonit.formacion.block6pathvariableheaders.controller;

import com.bosonit.formacion.block6pathvariableheaders.dto.Person;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AtomicLong counter = new AtomicLong();


    @PostMapping("/addUser")
    public ResponseEntity<Person> jsonPerson(@RequestBody Person person){
        //Al hacer un POST con addUser incrementamos el valor de id
        long currentCount = counter.incrementAndGet();
        //Creamos un nuevo Person con el valor del count sumado y el nombre
        //Con esto en el JSON no tendremos que poner el ID
        Person addPersonId = new Person(currentCount, person.name());
        return ResponseEntity.ok(addPersonId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getId(@PathVariable("id") long id){
        return ResponseEntity.ok(id);
    }

    @PutMapping ("/post")
    public HashMap<String, Integer> putMap(@RequestParam("var1") Integer var1, @RequestParam("var2") Integer var2) {
        HashMap<String, Integer> dataMap = new HashMap<>();
        dataMap.put("var1", var1);
        dataMap.put("var2", var2);
        return dataMap;
    }
}
