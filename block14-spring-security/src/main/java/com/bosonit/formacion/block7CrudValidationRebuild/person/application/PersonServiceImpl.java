package com.bosonit.formacion.block7CrudValidationRebuild.person.application;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.PersonERole;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.PersonRole;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInstructorOutpurDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonStudentOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto){

        Person newPerson = new Person(personInputDto);
        if (personInputDto.isAdministrator()){
            PersonRole adminRole = new PersonRole();
            adminRole.setRoleName(PersonERole.ADMIN);
            newPerson.setRoles(Set.of(adminRole));
        } else {
            PersonRole userRole = new PersonRole();
            userRole.setRoleName(PersonERole.USER);
            newPerson.setRoles(Set.of(userRole));
        }

        personRepository.save(newPerson);
        return new PersonOutputDto(newPerson);
    }

    @Override
    public List<PersonOutputDto> getAllPersons(int pageSize, int pageNumber, String outputType){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Person> personsPage = personRepository.findAll(pageRequest);
        List<PersonOutputDto> personFinalOutput = new ArrayList<>();

        for (Person person : personsPage){
            if(outputType.equals("full")){
                if(person.getStudent() != null){
                    personFinalOutput.add(new PersonStudentOutputDto(person));
                }
                if(person.getInstructor() != null){
                    personFinalOutput.add(new PersonInstructorOutpurDto(person));
                }
            } else personFinalOutput.add(new PersonOutputDto(person));
        }
        return personFinalOutput;
    }

    @Override
    public List<PersonOutputDto> getAllPersonsByUser(String personUser){
        return personRepository.findByPersonUser(personUser).stream().map(PersonOutputDto::new).toList();
    }
    @Override
    public PersonOutputDto updatePersonById(int id, PersonInputDto personInputDto){
        Person updatedPerson = personRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La persona con el id "+id+" no existe"));
        Person checkAndUpdatedPerson = checkAndSetUpdatedPerson(personInputDto, updatedPerson);
        personRepository.save(checkAndUpdatedPerson);
        return new PersonOutputDto(checkAndUpdatedPerson);
    }

    @Override
    public void deletePersonById(int id){
        personRepository.findById(id).orElseThrow(()->
                new RuntimeException("La persona con el id "+id+" no existe"));
        personRepository.deleteById(id);
    }

    //RestTemplate a 8081
    @Override
    public InstructorOutputDto getInstructor(int id){
        String url = "http://localhost:8081/profesor/"+id;
        try{
            ResponseEntity<InstructorOutputDto> response = restTemplate.exchange(url, HttpMethod.GET, null, InstructorOutputDto.class);
            if (response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            } else{
                throw new EntityNotFoundException("Instructor con id "+id+" no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //Métodos
    private static Person checkAndSetUpdatedPerson(PersonInputDto personInputDto, Person updatedPerson) {
        if (personInputDto.getPersonUser()!= null && !personInputDto.getPersonUser().equals("")){
            updatedPerson.setPersonUser(personInputDto.getPersonUser());
        }
        if (personInputDto.getName() != null && !personInputDto.getName().equals("")){
            updatedPerson.setName(personInputDto.getName());
        }
        if (personInputDto.getSurname() != null && !personInputDto.getSurname().equals("")){
            updatedPerson.setSurname(personInputDto.getSurname());
        }
        if (personInputDto.getCompanyEmail()!= null && !personInputDto.getCompanyEmail().equals("")){
            updatedPerson.setCompanyEmail(personInputDto.getCompanyEmail());
        }
        if (personInputDto.getPersonalEmail() != null && !personInputDto.getPersonalEmail().equals("")){
            updatedPerson.setPersonalEmail(personInputDto.getPersonalEmail());
        }
        if (personInputDto.getCity() != null && !personInputDto.getCity().equals("")){
            updatedPerson.setCity(personInputDto.getCity());
        }
        if (personInputDto.getActive() != null){
            updatedPerson.setActive(personInputDto.getActive());
        }
        if (personInputDto.getCreatedDate()!= null){
            updatedPerson.setCreatedDate(personInputDto.getCreatedDate());
        }
        if (personInputDto.getUrlImage() != null && !personInputDto.getUrlImage().equals("")){
            updatedPerson.setUrlImage(personInputDto.getUrlImage());
        }
        if (personInputDto.getTerminationDate() != null){
            updatedPerson.setTerminationDate(personInputDto.getTerminationDate());
        }
        return updatedPerson;
    }
}
