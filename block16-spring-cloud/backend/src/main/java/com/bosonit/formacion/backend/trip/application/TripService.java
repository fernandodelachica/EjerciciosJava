package com.bosonit.formacion.backend.trip.application;

import com.bosonit.formacion.backend.trip.domain.dto.TripInputDto;
import com.bosonit.formacion.backend.trip.domain.dto.TripOutputDto;

import java.util.List;

public interface TripService {

    TripOutputDto addTrip(TripInputDto tripInputDto);

    TripOutputDto updateTrip(String id, TripInputDto tripInputDto);

    TripOutputDto getTripById(String id);

    List<TripOutputDto> getAllTrips(int pageNumber, int pageSize);

    void deleteTripById(String id);

    void deletePassengerFromTripById(String clientId, String travelId);

    void addPassengerToTripById(String clientId, String travelId);

    Integer countPassengers(String travelId);

    void changeTripStatus(String tripId, String tripStatus);

    String getTripStatus(String tripId);
}
