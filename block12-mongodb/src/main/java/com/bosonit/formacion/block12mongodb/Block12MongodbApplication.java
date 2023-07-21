package com.bosonit.formacion.block12mongodb;

import com.bosonit.formacion.block12mongodb.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block12MongodbApplication {

	@Autowired
	PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(Block12MongodbApplication.class, args);
	}

}
