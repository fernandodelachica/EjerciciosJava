package com.bosonit.formacion.block7crud.repository;

import com.bosonit.formacion.block7crud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>, PagingAndSortingRepository<Person, Integer> {

    List<Person> findByNameLike(String name);

}
