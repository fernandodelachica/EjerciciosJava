package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClaseSecundaria implements CommandLineRunner {

    public String msg = "Estamos en la Clase secundaria!";
    public String datos = "¡Hola! somos datos que venimos de la Clase Secundaria";

    @Override
    public void run(String... args) throws Exception {
        System.out.println(msg);
    }
}
