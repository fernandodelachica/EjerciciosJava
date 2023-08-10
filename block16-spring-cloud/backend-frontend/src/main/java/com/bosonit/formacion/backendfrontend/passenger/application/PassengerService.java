package com.bosonit.formacion.backendfrontend.passenger.application;

import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerOutputDto;
import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;

import java.util.List;

public interface PassengerService {

    List<PassengerOutputDto> getPassengers(int pageNumber, int pageSize);

    PassengerOutputDto getPassengerById(String passengerId);

}
