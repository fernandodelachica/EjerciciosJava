package com.bosonit.formacion.block12mongodb.person.service;

import com.bosonit.formacion.block12mongodb.exception.EntityNotFoundException;
import com.bosonit.formacion.block12mongodb.person.domain.Person;
import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonInputDto;
import com.bosonit.formacion.block12mongodb.person.domain.dto.PersonOutputDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonOutputDto savePerson(PersonInputDto personInputDto){
        Person newPerson = mongoTemplate.save(new Person(personInputDto));
        return new PersonOutputDto(newPerson);
    }


    //Query con paginación
    @Override
    public List<PersonOutputDto> getAllPerson(int pageNumber, int pageSize){
        Query query = new Query();
        query.skip((pageNumber-1) * pageSize);
        query.limit(pageSize);

        return mongoTemplate.find(query, Person.class).stream().map(PersonOutputDto::new).toList();
    }

    @Override
    public PersonOutputDto findOnePersonById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Person personFound = mongoTemplate.findOne(query, Person.class);
        if(personFound != null){
            return new PersonOutputDto(personFound);
        } else {
            throw new EntityNotFoundException("El nombre "+id+" no se encuentra en la base de datos.");
        }
    }

    @Override
    public PersonOutputDto findOneByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Person personFound = mongoTemplate.findOne(query, Person.class);
        if(personFound != null){
            return new PersonOutputDto(personFound);
        } else {
            throw new EntityNotFoundException("El nombre "+name+" no se encuentra en la base de datos.");
        }
    }

    @Override
    public List<PersonOutputDto> findByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name, "i"));

        return mongoTemplate.find(query, Person.class).stream().map(PersonOutputDto::new).toList();
    }

    @Override
    public List<PersonOutputDto> findByBirthDateAfter(Date date){
        Query query = new Query();
        query.addCriteria(Criteria.where("dateOfBirth").gt(date));

        return mongoTemplate.find(query, Person.class).stream().map(PersonOutputDto::new).toList();
    }

    @Override
    public List<PersonOutputDto> findByAgeRange(int lowerBound, int upperBound){
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gt(lowerBound)
                .andOperator(Criteria.where("age").lt(upperBound)));

        return mongoTemplate.find(query, Person.class).stream().map(PersonOutputDto::new).toList();
    }

    @Override
    public void updateMultiplePersonAge(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update().inc("age", 1);
        mongoTemplate.findAndModify(query, update, Person.class);
    }

    @Override
    public void updateOnePersonById(String id, PersonInputDto personInputDto){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        if(personInputDto.getName() != null && !personInputDto.getName().equals("")){
            update.set("name", personInputDto.getName());
        }
        if(personInputDto.getAge() != null){
            update.set("age", personInputDto.getAge());
        }
        if(personInputDto.getFavoriteBooks() != null){
            update.set("favoriteBooks", personInputDto.getFavoriteBooks());
        }
        if(personInputDto.getDateOfBirth() != null){
            update.set("dateOfBirth", personInputDto.getDateOfBirth());
        }

        mongoTemplate.updateFirst(query, update, Person.class);
    }

    @Override
    public void addFavoriteBooksToPersonById(String id, PersonInputDto personInputDto){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.addToSet("favoriteBooks").each(personInputDto.getFavoriteBooks());

        mongoTemplate.updateFirst(query, update, Person.class);

    }


    @Override
    public void deletePersonById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.remove(query, Person.class);
    }

    public void deleteFavoriteBooksByNameAndId(String id, List<String> bookNames){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

        Update update = new Update();
        update.pullAll("favoriteBooks", bookNames.toArray());

        mongoTemplate.updateFirst(query, update, Person.class);
    }

}
