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
import java.util.function.Consumer;


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
    public PersonOutputDto addPerson(PersonInputDto personInputDto) throws UnprocessableEntityException {
        validateNotNullAndBlank(personInputDto.getPersonUser(), "El campo User no puede ser nulo", personInputDto::setPersonUser);
        validateNotNullAndBlank(personInputDto.getName(), "El campo Name no puede ser nulo", personInputDto::setName);
        validateNotNullAndBlank(personInputDto.getPassword(), "El campo Password no puede ser nulo", personInputDto::setPassword);
        validateNotNullAndBlank(personInputDto.getSurname(), "El campo Surname no puede ser nulo", personInputDto::setSurname);
        validateNotNullAndBlank(personInputDto.getCompanyEmail(), "El campo Company Email no puede ser nulo", personInputDto::setCompanyEmail);
        validateNotNullAndBlank(personInputDto.getPersonalEmail(), "El campo Personal Email no puede ser nulo", personInputDto::setPersonalEmail);
        validateNotNullAndBlank(personInputDto.getCity(), "El campo City no puede ser nulo", personInputDto::setCity);
        validateNotNullAndBlank(personInputDto.getCreatedDate(), "El campo Created Date no puede ser nulo", personInputDto::setCreatedDate);
        validateNotNull(personInputDto.getActive(), "El campo Active no puede ser nulo", personInputDto::setActive);

        return personRepository.save(new Person(personInputDto)).persontoPersonOutputDto();
    }
    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto person) throws UnprocessableEntityException{
        Person updatedPerson = personRepository.findById(id).orElseThrow();
        //-----------------------------value-----------------------------errorMessage--------------------------setter
        validateNotNullAndBlank(person.getPersonUser(), "El Usuario no puede ser nulo", updatedPerson::setPersonUser);
        validateNotNullAndBlank(person.getName(), "El campo Name no puede ser nulo", updatedPerson::setName);
        validateNotNullAndBlank(person.getPassword(), "El campo Password no puede ser nulo", updatedPerson::setPassword);
        validateNotNullAndBlank(person.getSurname(), "El campo Surname no puede ser nulo", updatedPerson::setSurname);
        validateNotNullAndBlank(person.getCompanyEmail(), "El campo Company Email no puede ser nulo", updatedPerson::setCompanyEmail);
        validateNotNullAndBlank(person.getPersonalEmail(), "El campo Personal Email no puede ser nulo", updatedPerson::setPersonalEmail);
        validateNotNullAndBlank(person.getCity(),"El campo City no puede ser nulo", updatedPerson::setCity);
        validateNotNullAndBlank(person.getCreatedDate(), "El campo Created Date no puede ser nulo", updatedPerson::setCreatedDate);
        validateNotNull(person.getActive(), "El campo Active no puede ser nulo", updatedPerson::setActive);

        return personRepository.save(updatedPerson).persontoPersonOutputDto();
    }

    /*Función para validar si es null o blank para ahorrar líneas de código
    * --Recibe 3 valores: value, errorMessage y la función setter
    * --Comprueba si el valor getVariableUser() no es nulo o no está vacío
    * ----Si es true, significa que se puede asignar y llama a setter
    * --------Consumer<T> que es una herramienta especial de Java y la <T> nos indica que el método puede manejar cualquier tipo de parámetro
    * ----Si es false, Lanza una excepción UnprocesasbleEntityException y el errorMessage es el que le pasamos como argumento
    * */
    private <T> void validateNotNullAndBlank(T value, String errorMessage, Consumer<T> setter) throws UnprocessableEntityException{
        if (value != null && !value.equals("")){
            setter.accept(value);
        } else throw new UnprocessableEntityException(errorMessage);
    }

    private <T> void validateNotNull(T value, String errorMessage, Consumer<T> setter) throws UnprocessableEntityException{
        if (value != null){
            setter.accept(value);
        } else throw new UnprocessableEntityException(errorMessage);
    }
}
