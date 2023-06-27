package com.bosonit.formacion.block7crud.service;

import com.bosonit.formacion.block7crud.model.Person;
import com.bosonit.formacion.block7crud.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crud.model.dto.PersonOutputDto;
import com.bosonit.formacion.block7crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    /*AÑADE DATOS A PERSON*/
    @Override
    public PersonOutputDto addPerson(PersonInputDto person){
        return personRepository.save(new Person(person)).persontoPersonOutputDto();
    }

    /*MUESTRA DATOS DE PERSON POR ID
    * - Busca por ID los datos y convierte los datos de person a PersonOutput y los muestra
    * */
    @Override
    public PersonOutputDto getPersonById(int id){
        return personRepository.findById(id).orElseThrow().persontoPersonOutputDto();
    }

    /*ACTUALIZA DATOS DE PERSON POR ID Y PARÁMETROS DEL BODY
    * - Busca por ID a las personas pasadas por parámetro
    * - Comprueba que no esté ni null ni en blanco para actualizar el dato
    * - Muestra el resultado final de los datos modificados
    * */
    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto person) {
        Person updatedPerson = personRepository.findById(id).orElseThrow();
        //Comprueba que los valores pasados por el Body no sean ni null ni estén en blanco
        if (person.getName() != null && !person.getName().equals("")){
            updatedPerson.setName(person.getName());
        }
        if (person.getAge() != null && !person.getAge().equals("")){
            updatedPerson.setAge(person.getAge());
        }
        if (person.getCity() != null && !person.getCity().equals("")){
            updatedPerson.setCity(person.getCity());
        }
        return personRepository.save(updatedPerson).persontoPersonOutputDto();
    }


    /*ELIMINA DATOS DE PERSON POR ID
    * - Busca en Person por ID
    * - Elimina los datos de Persona encontrados por ID
    * */
    @Override
    public void deletePersonById(int id){
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }

    /* BÚSQUEDA DE PERSONAS POR NOMBRE
    * stream() - Convierte el objeto "Iterable" en un flujo
    * map() - Transforma cada elemento del flujo a personToPersonOutputDto
    * toList() - Recopila los elementos del flujo en una lista
    * */
    @Override
    public List<PersonOutputDto> getAllPersonByNameLike(String name){
        return personRepository.findByNameLike("%"+name+"%").stream().map(Person::persontoPersonOutputDto).toList();
    }

    @Override
    public List<PersonOutputDto> getAllPerson(){
        return personRepository.findAll().stream().map(Person::persontoPersonOutputDto).toList();
    }
}
