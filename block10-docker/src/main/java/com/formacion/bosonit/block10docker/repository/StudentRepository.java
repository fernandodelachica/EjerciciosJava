package com.formacion.bosonit.block10docker.repository;

import com.formacion.bosonit.block10docker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
