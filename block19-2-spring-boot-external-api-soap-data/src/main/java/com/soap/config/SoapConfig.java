package com.soap.config;

import com.soap.application.SoapServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.soap.wsdl");
        return marshaller;
    }

    @Bean
    public SoapServiceImpl soapServiceImpl(Jaxb2Marshaller jaxb2Marshaller){
        SoapServiceImpl soapServiceImpl = new SoapServiceImpl();
        soapServiceImpl.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        soapServiceImpl.setMarshaller(jaxb2Marshaller);
        soapServiceImpl.setUnmarshaller(jaxb2Marshaller);
        return soapServiceImpl;
    }
}
