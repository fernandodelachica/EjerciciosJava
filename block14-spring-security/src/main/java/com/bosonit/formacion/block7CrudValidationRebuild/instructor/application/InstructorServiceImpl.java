package com.bosonit.formacion.block7CrudValidationRebuild.instructor.application;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorFullOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.dto.InstructorOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.repository.InstructorRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto){
        Instructor newInstructor = new Instructor(instructorInputDto);

        Person inputPerson = personRepository.findById(instructorInputDto.getPersonId()).orElseThrow(()->
                new EntityNotFoundException("Persona con el id "+instructorInputDto.getPersonId()+"no se ha encontrado"));

        newInstructor.setPerson(inputPerson);
        instructorRepository.save(newInstructor);
        return new InstructorOutputDto(newInstructor);
    }

    @Override
    public InstructorOutputDto updateInstructorById(int id, InstructorInputDto instructorInputDto){
        Instructor instructorToUpdate = instructorRepository.findById(id).orElseThrow(()->
            new EntityNotFoundException("El profesor con el id "+id+" no se ha encontrado"));
        Instructor updatedInstructor = checkAndSetInstructor(instructorInputDto, instructorToUpdate);
        instructorRepository.save(updatedInstructor);
        return new InstructorOutputDto(updatedInstructor);
    }

    @Override
    public List<InstructorOutputDto> getAllInstructors(int pageSize, int pageNumber, String outputType){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Instructor> instructorsPage = instructorRepository.findAll(pageRequest);
        List<InstructorOutputDto> instructorFinalOutput = new ArrayList<>();

        for(Instructor instructor : instructorsPage){
            if(outputType.equals("full")){
                instructorFinalOutput.add(new InstructorFullOutputDto(instructor));
            } else instructorFinalOutput.add(new InstructorOutputDto(instructor));
        }
        return instructorFinalOutput;
    }

    @Override
    public InstructorOutputDto getInstructorById(int id, String outputType){
        Instructor instructorFound = instructorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El profesor con el id "+id+" no se ha encontrado"));
        if(outputType.equals("full")){
            return new InstructorFullOutputDto(instructorFound);
        } else return new InstructorOutputDto(instructorFound);
    }

    public void deleteInstructorById(int id){
        instructorRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El profesor con el id "+id+" no se ha encontrado"));
        instructorRepository.deleteById(id);
    }

    private static Instructor checkAndSetInstructor(InstructorInputDto instructorInputDto, Instructor instructorToUpdate) {
        if(instructorInputDto.getComments() != null && !instructorInputDto.getComments().equals("")){
            instructorToUpdate.setComments(instructorInputDto.getComments());
        }
        if(instructorInputDto.getBranch() != null && !instructorInputDto.getBranch().equals("")){
            instructorToUpdate.setBranch(instructorInputDto.getBranch());
        }
        return instructorToUpdate;
    }
}
