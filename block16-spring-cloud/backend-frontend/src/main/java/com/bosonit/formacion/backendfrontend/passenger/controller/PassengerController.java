package com.bosonit.formacion.backendfrontend.passenger.controller;

import com.bosonit.formacion.backendfrontend.passenger.application.PassengerService;
import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    //Con RestTemplate
    @GetMapping
    public List<PassengerOutputDto> getAllPassengers(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return passengerService.getPassengers(pageNumber, pageSize);
    }

    //Con Feign
    @GetMapping("/{passengerId}")
    public PassengerOutputDto getPassengerById(@PathVariable String passengerId){
        return passengerService.getPassengerById(passengerId);
    }
}
