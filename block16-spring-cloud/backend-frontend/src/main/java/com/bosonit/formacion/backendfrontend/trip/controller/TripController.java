package com.bosonit.formacion.backendfrontend.trip.controller;

import com.bosonit.formacion.backendfrontend.trip.application.TripService;
import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    //Utilizando Feign
    @GetMapping
    public List<TripOutputDto> getAllTrips (
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return tripService.getAllTrips(pageNumber, pageSize);
    }

    //Utilizando RestTemplate
    @GetMapping("/{tripId}")
    public TripOutputDto getTripById(@PathVariable String tripId){
        return tripService.getTripById(tripId);
    }



}
