package com.bosonit.formacion.block7CrudValidationRebuild.instructor.repository;

import com.bosonit.formacion.block7CrudValidationRebuild.instructor.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>, PagingAndSortingRepository<Instructor, Integer> {
}
