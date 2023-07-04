package com.bosonit.formacion.block7crudvalidation.model.dto;

import com.bosonit.formacion.block7crudvalidation.model.Student;
import com.bosonit.formacion.block7crudvalidation.model.Subject;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutputDto {
    int studentTopicId;
    Integer instructorId;
    List<Integer> studentsIds = new ArrayList<>();
    String topic;
    String comment;
    LocalDate initialDate;
    LocalDate finishDate;


    public SubjectOutputDto (Subject subject){
        setStudentTopicId(subject.getStudentTopicId());
        setInstructorId(subject.getInstructor().getInstructorId());
        setTopic(subject.getTopic());
        setComment(subject.getComment());
        setInitialDate(subject.getInitialDate());
        setFinishDate(subject.getFinishDate());
        setStudentsIds(subject.getStudents().stream().map(Student::getStudentId).toList());
    }
}
