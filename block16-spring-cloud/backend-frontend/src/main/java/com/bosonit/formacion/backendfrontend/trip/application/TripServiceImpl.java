package com.bosonit.formacion.backendfrontend.trip.application;

import com.bosonit.formacion.backendfrontend.exception.EntityNotFoundException;
import com.bosonit.formacion.backendfrontend.feign.BackendFeign;
import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    private BackendFeign backendFeign;

    private final RestTemplate restTemplate;

    public TripServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    //Utilizando Feign
    @Override
    public List<TripOutputDto> getAllTrips(int pageNumber, int pageSize){
        return backendFeign.getAllTrips(pageNumber, pageSize);
    }

    //Utilizando RestTemplate
    @Override
    public TripOutputDto getTripById(String tripId){
        try{
            return restTemplate.getForObject("http://backend-service/trip/{tripId}", TripOutputDto.class, tripId);
        } catch (RestClientException e){
            throw new EntityNotFoundException("El viaje con el id "+tripId+" no existe");
        }
    }


}
