package com.bosonit.formacion.block6personcontrollers;

import com.bosonit.formacion.block6personcontrollers.dto.Person;
import com.bosonit.formacion.block6personcontrollers.service.CityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {

		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean
	public CityService cityService(){
		return new CityService();
	}
	//Asignamos los valores a los bean.
	@Bean("bean1")
	Person firstBean(){
		Person person = new Person("Julio", "Granada", 21);
		return person;
	}

	@Bean("bean2")
	Person secondBean(){
		Person person = new Person("Elisa", "Madrid", 24);
		return person;
	}

	@Bean("bean3")
	Person thirdBean(){
		Person person = new Person("Mauricio", "Barcelona", 42);
		return person;
	}
}
