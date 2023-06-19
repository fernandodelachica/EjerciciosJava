package com.bosonit.formacion.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block5LoggingApplication {

	private static final Logger logger = LoggerFactory.getLogger(Block5LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);


	}

}
