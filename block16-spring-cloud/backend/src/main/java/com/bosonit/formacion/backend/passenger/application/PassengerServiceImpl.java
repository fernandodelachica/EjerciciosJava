package com.bosonit.formacion.backend.passenger.application;

import com.bosonit.formacion.backend.passenger.domain.Passenger;
import com.bosonit.formacion.backend.passenger.domain.dto.PassengerInputDto;
import com.bosonit.formacion.backend.passenger.domain.dto.PassengerOutputDto;
import com.bosonit.formacion.backend.passenger.repository.PassengerRepository;
import com.bosonit.formacion.backend.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;


    @Override
    public PassengerOutputDto addClient(PassengerInputDto passengerInputDto){
        Passenger newPassenger = new Passenger(passengerInputDto);
        passengerRepository.save(newPassenger);
        return new PassengerOutputDto(newPassenger);
    }

    @Override
    public PassengerOutputDto updateClientById(String id, PassengerInputDto passengerInputDto){
        Passenger passengerToUpdate = findPassenger(id);

        checkAndUpdateClient(passengerInputDto, passengerToUpdate);

        passengerRepository.save(passengerToUpdate);
        return new PassengerOutputDto(passengerToUpdate);
    }



    @Override
    public List<PassengerOutputDto> getAllClients(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Passenger> clientsPage = passengerRepository.findAll(pageRequest);

        List<PassengerOutputDto> finalOutput = new ArrayList<>();

        for (Passenger passenger : clientsPage){
            finalOutput.add(new PassengerOutputDto(passenger));
        }
        return finalOutput;
    }

    @Override
    public PassengerOutputDto getClientById(String id){
        Passenger passengerFound = findPassenger(id);

        return new PassengerOutputDto(passengerFound);
    }

    public void deleteClientById(String id){
        passengerRepository.deleteById(id);
    }


    //Métodos extraidos
    private Passenger findPassenger(String id) {
        return passengerRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("El cliente con el id "+ id +" no existe."));
    }


    private static Passenger checkAndUpdateClient(PassengerInputDto passengerInputDto, Passenger passengerToUpdate) {
        if(passengerInputDto.getName() != null && !passengerInputDto.getName().equals("")){
            passengerToUpdate.setName(passengerInputDto.getName());
        }
        if(passengerInputDto.getLastName()!= null && !passengerInputDto.getLastName().equals("")){
            passengerToUpdate.setLastName(passengerInputDto.getLastName());
        }
        if(passengerInputDto.getAge() != null){
            passengerToUpdate.setAge(passengerInputDto.getAge());
        }
        if(passengerInputDto.getEmail() != null && !passengerInputDto.getEmail().equals("")){
            passengerToUpdate.setEmail(passengerInputDto.getEmail());
        }
        if(passengerInputDto.getTelephoneNumber() != null){
            passengerToUpdate.setTelephoneNumber(passengerInputDto.getTelephoneNumber());
        }
        return passengerToUpdate;
    }
}
