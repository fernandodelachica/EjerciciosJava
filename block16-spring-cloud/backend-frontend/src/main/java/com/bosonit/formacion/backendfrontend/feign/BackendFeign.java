package com.bosonit.formacion.backendfrontend.feign;

import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerOutputDto;
import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "backend-service")
public interface BackendFeign {


    //Feign de passenger
    @GetMapping("/passenger")
    List<PassengerOutputDto> getAllPassengers(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize);

    @GetMapping("/passenger/{passengerId}")
    PassengerOutputDto getPassengerById(@PathVariable String passengerId);



    //Feign de trip
    @GetMapping("/trip")
    List<TripOutputDto> getAllTrips(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize);

    @GetMapping("/trip/{tripId}")
    TripOutputDto getTripById(@PathVariable String tripId);

}
