package com.bosonit.formacion.block6personcontrollers.service;

import com.bosonit.formacion.block6personcontrollers.dto.Person;

public interface PersonService {
    Person createPerson(String name, String city, int age);
    Person getPerson();
}
