package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseTerciaria implements CommandLineRunner{
    @Autowired
    ClaseSecundaria claseSecundaria;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Desde la clase Terciaria decimos: " + claseSecundaria.datos);
        }
}
