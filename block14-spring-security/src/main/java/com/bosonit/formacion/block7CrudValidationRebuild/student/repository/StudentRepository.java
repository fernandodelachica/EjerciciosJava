package com.bosonit.formacion.block7CrudValidationRebuild.student.repository;

import com.bosonit.formacion.block7CrudValidationRebuild.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>, PagingAndSortingRepository<Student, Integer> {
}
