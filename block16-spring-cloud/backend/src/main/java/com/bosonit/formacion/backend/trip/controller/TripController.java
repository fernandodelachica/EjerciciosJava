package com.bosonit.formacion.backend.trip.controller;

import com.bosonit.formacion.backend.trip.application.TripService;
import com.bosonit.formacion.backend.trip.domain.dto.TripInputDto;
import com.bosonit.formacion.backend.trip.domain.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripOutputDto addTrip(@Valid @RequestBody TripInputDto tripInputDto){
        return tripService.addTrip(tripInputDto);
    }

    @PutMapping
    public TripOutputDto updateTrip(@RequestParam String id, @RequestBody TripInputDto tripInputDto){
        return tripService.updateTrip(id, tripInputDto);
    }

    @GetMapping
    public List<TripOutputDto> getAllTrips(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return tripService.getAllTrips(pageNumber, pageSize);
    }

    @GetMapping("/{tripId}")
    public TripOutputDto getTripById(@PathVariable String tripId){
        return tripService.getTripById(tripId);
    }

    @DeleteMapping("/{tripId}")
    public void deleteTripById(@PathVariable String tripId){
        tripService.deleteTripById(tripId);
    }

    @DeleteMapping("/deletePassenger")
    public void deletePassengerFromTripById(@RequestParam String clientId, @RequestParam String travelId){
        tripService.deletePassengerFromTripById(clientId, travelId);
    }

    @PostMapping("/addPassenger")
    public void addPassengerToTripById(@RequestParam String clientId, @RequestParam String travelId){
        tripService.addPassengerToTripById(clientId, travelId);
    }

    @GetMapping("/count/{tripId}")
    public Integer countClients(@PathVariable String tripId){
        return tripService.countPassengers(tripId);
    }

    @PutMapping("/{tripId}/{tripStatus}")
    public void changeTripStatus(@PathVariable String tripId, @PathVariable String tripStatus){
        tripService.changeTripStatus(tripId, tripStatus);
    }

    @GetMapping("/verify/{tripId}")
    public String getTripStatus(@PathVariable String tripId){
        return tripService.getTripStatus(tripId);
    }
}
