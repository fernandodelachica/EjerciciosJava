package com.bosonit.formacion.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {
    Logger logger = LoggerFactory.getLogger(Controlador.class);

    @RequestMapping("/")
    public String home(){
        logger.debug("Esto es un mensaje de DEBUG");
        logger.info("Esto es un mensaje de INFO");
        logger.error("Esto es un mensaje de ERROR");
        logger.warn("Esto es un mensaje de WARN");
        logger.trace("Esto es un mensaje de TRACE");
        return "Hola desde el Controlador";
    }
}
