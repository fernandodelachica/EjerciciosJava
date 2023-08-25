package com.formacion.mail.sender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Value(("${email.sender}"))
    private String emailUser;

    @Value(("${email.sender.password}"))
    private String emailPassword;


    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");                   //Host que va a utilizar el mail
        mailSender.setPort(587);
        mailSender.setUsername(emailUser);                      //Correo que va a enviar el email
        mailSender.setPassword(emailPassword);                   //No se debe exponer la contraseña

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");      //Indicamos el protocolo que se va a utilizar
        properties.put("mail.smtp.out", true);                  //Habilitamos la autenticación
        properties.put("mail.smtp.starttls.enable", true);             //Habilitamos el cifrado entre comunicación de app y correo
        properties.put("mail.debug", true);                     //Nos imprime en la consola debugs sobre la comunicación

        return mailSender;
    }

}
