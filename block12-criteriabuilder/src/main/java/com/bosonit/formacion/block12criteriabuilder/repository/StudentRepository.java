package com.bosonit.formacion.block12criteriabuilder.repository;

import com.bosonit.formacion.block12criteriabuilder.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
