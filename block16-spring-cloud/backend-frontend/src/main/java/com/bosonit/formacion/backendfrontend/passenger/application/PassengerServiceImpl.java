package com.bosonit.formacion.backendfrontend.passenger.application;

import com.bosonit.formacion.backendfrontend.exception.EntityNotFoundException;
import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerOutputDto;
import com.bosonit.formacion.backendfrontend.feign.BackendFeign;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    BackendFeign backendFeign;

    private final RestTemplate restTemplate;

    public PassengerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    //Utilizando RestTemplate
    @Override
    public List<PassengerOutputDto> getPassengers(int pageNumber, int pageSize){
        String url = "http://backend-service/passenger?pageNumber="+pageNumber+"&pageSize="+pageSize;
        List<PassengerOutputDto> listOfPassenger =  restTemplate.getForObject(url, List.class, pageNumber, pageSize);
        return listOfPassenger;
    }

    //Utilizando Feign
    @Override
    public PassengerOutputDto getPassengerById(String passengerId){
        try{
            return backendFeign.getPassengerById(passengerId);
        } catch (FeignException.NotFound feignNotFound){
            throw new EntityNotFoundException("El pasajero con el id "+passengerId+" no existe");
        }
    }
}
