package com.bosonit.formacion.backendfrontend.trip.application;

import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;

import java.util.List;

public interface TripService {

    TripOutputDto getTripById(String tripId);

    List<TripOutputDto> getAllTrips(int pageNumber, int pageSize);
}
