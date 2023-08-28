package com.formacion.soap.config;

import com.formacion.soap.client.SoapServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    /*Marshaller convierte de XML a Java y de Java a XML, es un traductor
    *
    * * * En el pom.xml hemos pasado de XML a Objetos Java
    * * * Ahora pasamos de Objetos Java a XML
    * */
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.formacion.soap.wsdl");
        return marshaller;
    }

    @Bean
    public SoapServiceImpl getSoapClient(Jaxb2Marshaller marshaller){
        SoapServiceImpl soapServiceImpl = new SoapServiceImpl();
        soapServiceImpl.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        soapServiceImpl.setMarshaller(marshaller);
        soapServiceImpl.setUnmarshaller(marshaller);

        return soapServiceImpl;
    }
}
