package com.bosonit.formacion.block6personcontrollers.controller;

import com.bosonit.formacion.block6personcontrollers.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller3")
public class Controller3 {
    @Autowired
    @Qualifier("bean1")
    private Person firstBean;
    @Autowired
    @Qualifier("bean2")
    private Person secondBean;
    @Autowired
    @Qualifier("bean3")
    private Person thirdBean;

    @GetMapping("/bean/{bean}")
    public ResponseEntity<Person> getBean(@PathVariable String bean){
        Person person;
        //Analiza el dato que le pasamos por {bean} y según el valor asigna a person la variable correspondiente
        switch (bean) {
            case "bean1":
                person = firstBean;
                break;
            case "bean2":
                person = secondBean;
                break;
            case "bean3":
                person = thirdBean;
                break;
            //En el caso de que se introduzca un bean que no exista, lanzará una Respuesta de 404 - NOT FOUND
            default:
                return ResponseEntity.notFound().build();
        }
        //Responde con un 200-Con la variable del bean asignado
        return ResponseEntity.ok(person);
    }
}
