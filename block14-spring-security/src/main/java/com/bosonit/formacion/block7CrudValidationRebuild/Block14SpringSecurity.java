package com.bosonit.formacion.block7CrudValidationRebuild;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.PersonERole;
import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.PersonRole;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
@EnableFeignClients
public class Block14SpringSecurity {

	public static void main(String[] args) {
		SpringApplication.run(Block14SpringSecurity.class, args);
	}


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	PersonRepository personRepository;

	//Genera un usuario para realizar pruebas con el rol de ADMIN
	@Bean
	CommandLineRunner init(){
		return args -> {
			Person testPerson = new Person();
			testPerson.setPersonUser("administrator");
			testPerson.setPassword(passwordEncoder.encode("administrator"));
			testPerson.setName("Administrador");
			testPerson.setSurname("AdminSurname");
			testPerson.setPersonalEmail("administrator@personal.com");
			testPerson.setCompanyEmail("administrator@company.com");
			testPerson.setCity("Jaén");
			testPerson.setActive(true);
			testPerson.setCreatedDate(new Date());
			testPerson.setAdministrator(true);
			testPerson.setUrlImage("https://google.com/imagen.png");
			testPerson.setTerminationDate(null);
			testPerson.setRoles(Set.of(PersonRole.builder()
											.roleName(PersonERole.valueOf(PersonERole.ADMIN.name()))
											.build()));
			personRepository.save(testPerson);
		};
	}

}