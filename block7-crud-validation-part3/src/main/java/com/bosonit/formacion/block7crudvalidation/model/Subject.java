package com.bosonit.formacion.block7crudvalidation.model;

import com.bosonit.formacion.block7crudvalidation.model.dto.SubjectInputDto;
import com.bosonit.formacion.block7crudvalidation.repository.StudentRepository;
import com.bosonit.formacion.block7crudvalidation.service.StudentService;
import com.bosonit.formacion.block7crudvalidation.service.SubjectService;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer subjectId;
    String subjectName;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="instructorId")
    Instructor instructor;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    Set<Student> students;



    public Subject(SubjectInputDto subjectInputDto){
        this.subjectName = subjectInputDto.getSubjectName();
        this.comment = subjectInputDto.getComment();
        this.initialDate = subjectInputDto.getInitialDate();
        this.finishDate = subjectInputDto.getInitialDate();
    }

}
