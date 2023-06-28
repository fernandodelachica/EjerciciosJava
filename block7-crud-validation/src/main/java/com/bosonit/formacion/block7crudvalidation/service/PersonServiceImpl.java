package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.model.Person;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;
    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) throws Exception{
        if (personInputDto.getPersonUser() == null || personInputDto.getPersonUser().equals("")){
            throw new Exception("Usuario no puede ser nulo");
        }
        if (personInputDto.getPersonUser().length() > 10 || personInputDto.getPersonUser().length() <6){
            throw new Exception("El usuario tiene que tener entre 6 y 10 caracteres");
        }

        if (personInputDto.getPassword() == null || personInputDto.getPassword().equals("")){
            throw new Exception("La contraseña no puede ser nula");
        }

        if(personInputDto.getName() == null || personInputDto.getName().equals("")){
            throw new Exception("El nombre no puede ser nulo");
        }

        if(personInputDto.getCompanyEmail() == null || personInputDto.getCompanyEmail().equals("")){
            throw new Exception("Company_email no puede ser nulo");
        }

        if(personInputDto.getPersonalEmail() == null || personInputDto.getPersonalEmail().equals("")){
            throw new Exception("Personal_email no puede ser nulo");
        }

        if(personInputDto.getCity() == null || personInputDto.getCity().equals("")){
            throw new Exception ("City no puede ser nulo");
        }

        if(personInputDto.getActive() == null){
            throw new Exception("Active no puede ser nulo");
        }

        if(personInputDto.getCreatedDate() == null){
            throw new Exception("Created date no puede ser nulo");
        }

        return personRepository.save(new Person(personInputDto)).persontoPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id){
        return personRepository.findById(id).orElseThrow().persontoPersonOutputDto();
    }

    @Override
    public List<PersonOutputDto> getAllPerson(){
        return personRepository.findAll().stream().map(Person::persontoPersonOutputDto).toList();
    }
    @Override
    public List<PersonOutputDto> getAllPersonByUser(String personUser){
        return personRepository.findByPersonUser(personUser).stream().map(Person::persontoPersonOutputDto).toList();
    }
}
