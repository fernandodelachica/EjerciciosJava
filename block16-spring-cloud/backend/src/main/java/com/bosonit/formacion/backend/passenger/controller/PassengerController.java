package com.bosonit.formacion.backend.passenger.controller;

import com.bosonit.formacion.backend.passenger.application.PassengerService;
import com.bosonit.formacion.backend.passenger.domain.dto.PassengerInputDto;
import com.bosonit.formacion.backend.passenger.domain.dto.PassengerOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerOutputDto addPassenger(@Valid @RequestBody PassengerInputDto passengerInputDto){
        return passengerService.addClient(passengerInputDto);
    }

    @PutMapping
    public PassengerOutputDto updatePassenger(@RequestParam String id, @RequestBody PassengerInputDto passengerInputDto){
        return passengerService.updateClientById(id, passengerInputDto);
    }

    @GetMapping("/{passengerId}")
    public PassengerOutputDto getPassengerById(@PathVariable String passengerId){
        return passengerService.getClientById(passengerId);
    }

    @GetMapping
    public List<PassengerOutputDto> getAllPassengers(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return passengerService.getAllClients(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deletePassengerById(@PathVariable String id){
        passengerService.deleteClientById(id);
    }



}
