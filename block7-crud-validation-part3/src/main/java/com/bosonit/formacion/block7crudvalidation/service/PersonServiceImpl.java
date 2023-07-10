package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Person;
import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.dto.*;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<PersonOutputDto> getPersons(String outputType){
        List<PersonOutputDto> personsList = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            if (outputType.equals("full")){
                if(person.getStudent() != null){
                    Student student = person.getStudent();
                    PersonStudentOutputDto dto = new PersonStudentOutputDto(person);
                    dto.setStudent(new StudentOutputDto(student));
                    personsList.add(dto);
                }
                if (person.getInstructor() != null){
                    Instructor instructor = person.getInstructor();
                    PersonInstructorOutputDto dto = new PersonInstructorOutputDto(person);
                    dto.setInstructor(new InstructorOutputDto(instructor));
                    personsList.add(dto);
                }
            } else personsList.add(new PersonOutputDto(person));
        });
        return personsList;
    }
    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        checkPerson(personInputDto);
        return personRepository.save(new Person(personInputDto)).persontoPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id, String outputType){
        Person person = personRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La persona con el id "+id+" no se ha encontrado"));

        if(outputType.equals("full")){
            if(person.getStudent() != null){
                Student student = person.getStudent();
                PersonStudentOutputDto dto = new PersonStudentOutputDto(person);
                dto.setStudent(new StudentOutputDto(student));
                return dto;
            }
            if(person.getInstructor() != null){
                Instructor instructor = person.getInstructor();
                PersonInstructorOutputDto dto = new PersonInstructorOutputDto(person);
                dto.setInstructor(new InstructorOutputDto(instructor));
                return dto;
            }
        }
        return new PersonOutputDto(person);
    }

    @Override
    public List<PersonOutputDto> getAllPerson(){
        return personRepository.findAll().stream().map(Person::persontoPersonOutputDto).toList();
    }

    @Override
    public List<PersonOutputDto> getAllPersonByUser(String personUser){
        return personRepository.findByPersonUser(personUser).stream().map(Person::persontoPersonOutputDto).toList();
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
        personRepository.save(updatedPerson);
        return new PersonOutputDto(updatedPerson);
    }

    @Override
    public InstructorOutputDto getInstructor(int id){
        String url = "http://localhost:8081/profesor/"+id;
        try{
            ResponseEntity<InstructorOutputDto> response = restTemplate.exchange(url, HttpMethod.GET, null, InstructorOutputDto.class);
            if (response.getStatusCode() == HttpStatus.OK){
                return response.getBody();
            } else{
                return new InstructorOutputDto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
