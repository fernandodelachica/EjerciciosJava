package com.bosonit.formacion.block7crudvalidation.service;

import com.bosonit.formacion.block7crudvalidation.exception.EntityNotFoundException;
import com.bosonit.formacion.block7crudvalidation.exception.UnprocessableEntityException;
import com.bosonit.formacion.block7crudvalidation.model.Instructor;
import com.bosonit.formacion.block7crudvalidation.model.Person;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorInputDto;
import com.bosonit.formacion.block7crudvalidation.model.dto.InstructorOutputDto;
import com.bosonit.formacion.block7crudvalidation.repository.InstructorRepository;
import com.bosonit.formacion.block7crudvalidation.repository.PersonRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto){
        Instructor instructor = new Instructor();
        Person personFound = personRepository.findById(instructorInputDto.getPersonId()).orElseThrow(()->
                new EntityNotFoundException("Persona con el id "+instructorInputDto.getInstructorId()+"no se ha encontrado"));
        instructor.setPerson(personFound);
        instructor.setComments(instructorInputDto.getComments());
        instructor.setBranch(instructorInputDto.getBranch());
        return instructorRepository.save(instructor).instructorToInstructorOutputDto();
    }

    @Override
    public List<InstructorOutputDto> getAllInstructors(){
        return instructorRepository.findAll().stream().map(Instructor::instructorToInstructorOutputDto).toList();
    }

    @Override
    public InstructorOutputDto updateInstructor(int id, InstructorInputDto instructorInputDto){
        Instructor updatedInstructor = instructorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El instructor con el id "+id+" no se ha encontrado"));
        if(instructorInputDto.getComments() != null && !instructorInputDto.getComments().equals("")){
            updatedInstructor.setComments(instructorInputDto.getComments());
        }
        if(instructorInputDto.getBranch() != null && !instructorInputDto.getBranch().equals("")){
            updatedInstructor.setBranch(instructorInputDto.getBranch());
        }
        return instructorRepository.save(updatedInstructor).instructorToInstructorOutputDto();
    }

    @Override
    public void deleteInstructor(int id){
        instructorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El instructor con el id "+id+" no se ha encontrado"));
        instructorRepository.deleteById(id);
    }
}
