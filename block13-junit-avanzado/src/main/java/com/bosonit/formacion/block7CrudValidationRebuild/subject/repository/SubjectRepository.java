package com.bosonit.formacion.block7CrudValidationRebuild.subject.repository;

import com.bosonit.formacion.block7CrudValidationRebuild.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
