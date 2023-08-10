package com.bosonit.formacion.backend.trip.application;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import com.bosonit.formacion.backend.passenger.repository.PassengerRepository;
import com.bosonit.formacion.backend.exception.EntityNotFoundException;
import com.bosonit.formacion.backend.exception.UnprocessableEntityException;
import com.bosonit.formacion.backend.trip.domain.Trip;
import com.bosonit.formacion.backend.trip.domain.dto.TripInputDto;
import com.bosonit.formacion.backend.trip.domain.dto.TripOutputDto;
import com.bosonit.formacion.backend.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public TripOutputDto addTrip(TripInputDto tripInputDto){
        Trip newTrip = new Trip(tripInputDto);

        Set<Passenger> passengers = new HashSet<>();
        for(String clientId : tripInputDto.getClients()){
            Passenger passenger = findPassenger(clientId);
            passengers.add(passenger);
        }

        if(tripInputDto.isAvailable()){
            newTrip.setStatus("DISPONIBLE");
        } else newTrip.setStatus("AVERIADO");

        newTrip.setPassengers(passengers);
        tripRepository.save(newTrip);
        return new TripOutputDto(newTrip);
    }



    @Override
    public TripOutputDto updateTrip(String id, TripInputDto tripInputDto){
        Trip tripToUpdate = findTrip(id);

        checkAndUpdateTravel(tripInputDto, tripToUpdate);
        tripRepository.save(tripToUpdate);

        return new TripOutputDto(tripToUpdate);
    }

    @Override
    public TripOutputDto getTripById(String id){
        Trip trip = findTrip(id);
        return new TripOutputDto(trip);
    }

    @Override
    public List<TripOutputDto> getAllTrips(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Trip> travelsPage = tripRepository.findAll(pageRequest);

        List<TripOutputDto> finalOutput = new ArrayList<>();

        for (Trip trip : travelsPage){
            finalOutput.add(new TripOutputDto(trip));
        }
        return finalOutput;
    }

    @Override
    public void deleteTripById(String id){
        tripRepository.deleteById(id);
    }

    @Override
    public void deletePassengerFromTripById(String clientId, String travelId){
        Trip trip = findTrip(travelId);

        boolean clientRemoved = trip.getPassengers().removeIf(client -> client.getPassengerId().equals(clientId));

        if(clientRemoved){
            tripRepository.save(trip);
        } else throw new UnprocessableEntityException("El cliente con el id "+clientId+" no está en el viaje con el id "+travelId);
    }

    @Override
    public void addPassengerToTripById(String clientId, String travelId){
        Trip trip = findTrip(travelId);

        Passenger passenger = findPassenger(clientId);

        //Comprueba si el Client ya está en el Viaje y si está completo
        if(!trip.getPassengers().contains(passenger) && trip.getPassengers().size() < 40){
            trip.getPassengers().add(passenger);
            tripRepository.save(trip);
        } else if (trip.getPassengers().contains(passenger)){
            throw new UnprocessableEntityException("El cliente con el id "+clientId+" ya está en el viaje "+travelId);
        } else if (trip.getPassengers().size() == 40){
            throw new UnprocessableEntityException("El viaje con el id "+travelId+" está completo");
        }
    }

    @Override
    public Integer countPassengers(String travelId){
        Trip trip = findTrip(travelId);
        return trip.getPassengers().size();
    }

    @Override
    public void changeTripStatus(String tripId, String tripStatus){
        Trip trip = findTrip(tripId);

        String statusAvailable = "DISPONIBLE";
        String statusDamaged = "AVERIADO";

        if(tripStatus.toUpperCase().equals(statusAvailable)){
            trip.setStatus(statusAvailable);
        } else if (tripStatus.toUpperCase().equals(statusDamaged)){
            trip.setStatus(statusDamaged);
        } else throw new UnprocessableEntityException("Tipo de estado no permitido.");

        tripRepository.save(trip);
    }

    @Override
    public String getTripStatus(String tripId){
        Trip trip = findTrip(tripId);

        return trip.getStatus();
    }




    //Métodos extraídos
    private Passenger findPassenger(String passengerId) {
       return passengerRepository.findById(passengerId).orElseThrow(()->
                new EntityNotFoundException("El cliente con el id "+ passengerId +" no existe."));
    }

    private Trip findTrip(String travelId) {
        return tripRepository.findById(travelId).orElseThrow(() ->
                new EntityNotFoundException("El viaje con el id " + travelId + " no existe."));
    }

    private Trip checkAndUpdateTravel(TripInputDto tripInputDto, Trip tripToUpdate) {
        if(!tripInputDto.getOrigin().isEmpty()){
            tripToUpdate.setOrigin(tripInputDto.getOrigin());
        }
        if(!tripInputDto.getDestination().isEmpty()){
            tripToUpdate.setDestination(tripInputDto.getDestination());
        }
        if(tripInputDto.getDepartureDate() != null){
            tripToUpdate.setDepartureDate(tripInputDto.getDepartureDate());
        }
        if(tripInputDto.getArrivalDate() != null){
            tripToUpdate.setArrivalDate(tripInputDto.getArrivalDate());
        }

        return tripToUpdate;
    }
}
