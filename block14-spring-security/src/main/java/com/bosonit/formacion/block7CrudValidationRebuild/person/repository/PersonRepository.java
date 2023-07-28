package com.bosonit.formacion.block7CrudValidationRebuild.person.repository;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer>, PagingAndSortingRepository<Person, Integer> {

    Optional<Person> findByPersonUser(String personUser);

    List<PersonOutputDto> getPersonQuery(
            HashMap<String, Object> conditions,
            int pageNumber, int pageSize);

}
