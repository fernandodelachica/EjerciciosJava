package com.bosonit.formacion.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LecturaProperties implements CommandLineRunner {
    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int number;

    @Value("${new.property: new.property no tiene valor}")
    private String newProperty;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("El valor de greeting es: "+greeting);
        System.out.println("El valor de nombre es: "+number);
        System.out.println("El valor de new.property es: "+newProperty);
    }
}
