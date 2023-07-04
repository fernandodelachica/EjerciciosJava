package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
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
    public PersonOutputDto getPersonById(int id){
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La persona con el id: "+id+" no existe")).persontoPersonOutputDto();
    }

    @Override
    public List<PersonOutputDto> getAllPerson(){
        return personRepository.findAll().stream().map(Person::persontoPersonOutputDto).toList();
    }

    @Override
    public List<PersonOutputDto> getAllPersonByUser(String personUser){
        return personRepository.findByPersonUser(personUser).stream().map(Person::persontoPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        checkPerson(personInputDto);
        return personRepository.save(new Person(personInputDto)).persontoPersonOutputDto();
    }

    private static void checkPerson(PersonInputDto personInputDto) {
        if (personInputDto.getPersonUser() == null || personInputDto.getPersonUser().equals("")){
            throw new UnprocessableEntityException("El campo personUser es nulo o no contiene un valor");
        }
        if (personInputDto.getPassword() == null || personInputDto.getPassword().equals("")){
            throw new UnprocessableEntityException("El campo Password es nulo o no contiene un valor");
        }
        if (personInputDto.getName() == null || personInputDto.getName().equals("")){
            throw new UnprocessableEntityException("El campo Usuario es nulo o no contiene un valor");
        }
        if (personInputDto.getCompanyEmail() == null || personInputDto.getCompanyEmail().equals("")){
            throw new UnprocessableEntityException("El campo CompanyEmail es nulo o no contiene un valor");
        }
        if (personInputDto.getCity() == null || personInputDto.getCity().equals("")){
            throw new UnprocessableEntityException("El campo PersonalEmail es nulo o no contiene un valor");
        }
        if (personInputDto.getActive() == null){
            throw new UnprocessableEntityException("El campo Acive es nulo");
        }
        if (personInputDto.getCreatedDate() == null){
            throw new UnprocessableEntityException("El campo CreatedDate es nulo");
        }
    }

    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto personInputDto){
        Person updatedPerson = personRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La persona con el id "+id+" no se ha encontrado"));
        checkPerson(personInputDto);
        return personRepository.save(updatedPerson).persontoPersonOutputDto();
    }



}
