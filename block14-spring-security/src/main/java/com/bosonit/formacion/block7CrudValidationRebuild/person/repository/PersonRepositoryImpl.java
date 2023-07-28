package com.bosonit.formacion.block7CrudValidationRebuild.person.repository;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.dto.PersonOutputDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    //Ejercicio de Criteria Builder
    public List<PersonOutputDto> getPersonQuery(
            HashMap<String, Object> conditions,
            int pageNumber, int pageSize){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value)->{
            //SELECT * FROM PERSON WHERE personUser LIKE value
            if(field.equals("personUser")){
                predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
            }
            //SELECT * FROM PERSON WHERE name LIKE value
            if(field.equals("name")){
                predicates.add(cb.like(root.get(field), "%" + (String) value +"%"));
            }
            //SELECT * FROM PERSON WHERE surname LIKE value
            if(field.equals("surname")){
                predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
            }

            if(field.equals("createdDate")){
                String dateCondition = (String) conditions.get("dateCondition");
                switch (dateCondition){
                    //SELECT * FROM PERSON WHERE createdDate > value
                    case "GREATHER":
                        predicates.add(cb.greaterThan(root.<LocalDate>get(field), (LocalDate) value));
                        break;
                    //SELECT * FROM PERSON WHERE createdDate < value
                    case "LESS":
                        predicates.add(cb.lessThan(root.<LocalDate>get(field), (LocalDate) value));
                        break;
                }
            }

            String orderBy = (String) conditions.get("orderBy");
            if(field.equals("orderBy")){
                switch (orderBy){
                    //Añade ORDER BY personUser ASC
                    case "personUser":
                        query.orderBy(cb.asc(root.get("personUser")));
                        break;
                    //Añade ORDER BY name ASC
                    case "name":
                        query.orderBy(cb.asc(root.get("name")));
                        break;
                }
            }
        });

        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));


        return entityManager.createQuery(query)
                .setFirstResult((pageNumber-1) * pageSize)  //Paginación de la sentencia, por defecto 1 para que imprima la [0]
                .setMaxResults(pageSize)                    //Valor de los máximos datos imprimidos en pantalla (10 por defecto)
                .getResultList()
                .stream().map(PersonOutputDto::new).toList();
    }
}
