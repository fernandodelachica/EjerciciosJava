package com.bosonit.formacion.block7CrudValidationRebuild.subject.application;

import com.bosonit.formacion.block7CrudValidationRebuild.exception.EntityNotFoundException;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import com.bosonit.formacion.block7CrudValidationRebuild.instructor.repository.InstructorRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import com.bosonit.formacion.block7CrudValidationRebuild.student.repository.StudentRepository;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectInputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.dto.SubjectOutputDto;
import com.bosonit.formacion.block7CrudValidationRebuild.subject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    InstructorRepository instructorRepository;

    private static final String NOTFOUNDSTRING = " no se ha encontrado.";

    @Override
    public SubjectOutputDto addSubject(SubjectInputDto subjectInputDto){
        Subject newSubject = new Subject(subjectInputDto);

        Instructor instructor = instructorRepository.findById(subjectInputDto.getInstructorId()).orElseThrow(()->
                new EntityNotFoundException("El id del profesor "+subjectInputDto.getInstructorId()+ NOTFOUNDSTRING));

        Set<Student> students = findAndAddStudents(subjectInputDto);

        newSubject.setInstructor(instructor);
        newSubject.setStudents(students);
        subjectRepository.save(newSubject);
        return new SubjectOutputDto(newSubject);
    }
    @Override
    public SubjectOutputDto updateSubjectById(int id, SubjectInputDto subjectInputDto){
        Subject subjectFound = subjectRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La asignatura con el id "+id+ NOTFOUNDSTRING ));

        Subject updatedSubject = checkAndUpdateSubject(subjectInputDto, subjectFound);
        subjectRepository.save(updatedSubject);
        return new SubjectOutputDto(updatedSubject);
    }

    @Override
    public List<SubjectOutputDto> getAllSubjects(int pageSize, int pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        return subjectRepository.findAll(pageRequest).stream().map(SubjectOutputDto::new).toList();
    }

    @Override
    public SubjectOutputDto getSubjectById(int id){
        return new SubjectOutputDto(subjectRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("La asignatura con el id "+id+ NOTFOUNDSTRING)));
    }

    public void deleteSubjectById(int id){
        subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectOutputDto> getSubjectStudentById(int studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new EntityNotFoundException("El Estudiante con el id "+studentId+ NOTFOUNDSTRING));
        return student.getSubjects().stream().map(SubjectOutputDto::new).toList();
    }


    //Métodos
    private Subject checkAndUpdateSubject(SubjectInputDto subjectInputDto, Subject subjectFound) {
        if(subjectInputDto.getInstructorId() != null){
            Instructor updatedInstructor = instructorRepository.findById(subjectInputDto.getInstructorId()).orElseThrow(()->
                    new EntityNotFoundException("El profesor con el id "+ subjectInputDto.getInstructorId()+ NOTFOUNDSTRING));
            subjectFound.setInstructor(updatedInstructor);
        }
        if (!subjectInputDto.getStudents().isEmpty()){
            subjectFound.setStudents(findAndAddStudents(subjectInputDto));
        }
        if (subjectInputDto.getSubjectName() != null && !subjectInputDto.getSubjectName().equals("")){
            subjectFound.setSubjectName(subjectInputDto.getSubjectName());
        }
        if(subjectInputDto.getComment() != null && !subjectInputDto.getComment().equals("")){
            subjectFound.setComment(subjectInputDto.getComment());
        }
        if(subjectInputDto.getInitialDate() != null){
            subjectFound.setInitialDate(subjectInputDto.getInitialDate());
        }
        if(subjectInputDto.getFinishDate() != null){
            subjectFound.setFinishDate(subjectInputDto.getFinishDate());
        }
        return subjectFound;
    }

    private Set<Student> findAndAddStudents(SubjectInputDto subjectInputDto) {
        Set<Student> students = new HashSet<>();
        for (Integer  studentId : subjectInputDto.getStudents()){
            Student student = studentRepository.findById(studentId).orElseThrow(()->
                    new EntityNotFoundException("El id del estudiante "+studentId+ NOTFOUNDSTRING));
            students.add(student);
        }
        return students;
    }
}
