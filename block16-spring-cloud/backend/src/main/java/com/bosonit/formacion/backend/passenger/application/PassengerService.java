package com.bosonit.formacion.backend.passenger.application;

import com.bosonit.formacion.backend.passenger.domain.dto.PassengerInputDto;
import com.bosonit.formacion.backend.passenger.domain.dto.PassengerOutputDto;

import java.util.List;

public interface PassengerService {

    PassengerOutputDto addClient(PassengerInputDto passengerInputDto);

    PassengerOutputDto updateClientById(String id, PassengerInputDto passengerInputDto);

    List<PassengerOutputDto> getAllClients(int pageNumber, int pageSize);

    PassengerOutputDto getClientById(String id);

    void deleteClientById(String id);
}
