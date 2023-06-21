package com.bosonit.formacion.block6personcontrollers.service;

import com.bosonit.formacion.block6personcontrollers.dto.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{
    private Person person;
    @Override
    public Person createPerson(String name, String city, int age){
        person = new Person();
        person.setName(name);;
        person.setCity(city);
        person.setAge(age);
        return person;
    }

    @Override
    public Person getPerson() {
        return person;
    }


}
