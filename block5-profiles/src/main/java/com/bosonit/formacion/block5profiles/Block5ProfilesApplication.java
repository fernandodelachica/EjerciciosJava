package com.bosonit.formacion.block5profiles;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner{

	@Autowired
	AppConfig appConfig;


	public static void main(String[] args) {

		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args){
		log.info("Estamos en {}", appConfig.getUrl());
	}
}
