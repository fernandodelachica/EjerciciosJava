package com.bosonit.formacion.backendfrontend.ticket.application;

import com.bosonit.formacion.backendfrontend.exception.EntityNotFoundException;
import com.bosonit.formacion.backendfrontend.passenger.application.PassengerService;
import com.bosonit.formacion.backendfrontend.passenger.domain.dto.PassengerOutputDto;
import com.bosonit.formacion.backendfrontend.ticket.domain.Ticket;
import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketInputDto;
import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketOutputDto;
import com.bosonit.formacion.backendfrontend.ticket.repository.TicketRepository;
import com.bosonit.formacion.backendfrontend.trip.application.TripService;
import com.bosonit.formacion.backendfrontend.trip.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TripService tripService;

    @Autowired
    PassengerService passengerService;

    @Override
    public TicketOutputDto generateTicket(String passengerId, String tripId){
        //Utilizando Feign alojado en PassengerService
        PassengerOutputDto passenger = passengerService.getPassengerById(passengerId);

        //Utilizando RestTemplate alojado en TripService
        TripOutputDto trip = tripService.getTripById(tripId);

        //Coge los datos extraídos del backend y crea un ticket seteando los valores
        TicketInputDto ticketInputDto = new TicketInputDto();
        ticketInputDto.setPassengerId(passenger.getPassengerId());
        ticketInputDto.setPassengerLastName(passenger.getLastName());
        ticketInputDto.setPassengerEmail(passenger.getEmail());
        ticketInputDto.setTripOrigin(trip.getOrigin());
        ticketInputDto.setTripDestination(trip.getDestination());
        ticketInputDto.setDepartureDate(trip.getDepartureDate());
        ticketInputDto.setArrivalDate(trip.getArrivalDate());

        Ticket ticket = new Ticket(ticketInputDto);

        ticketRepository.save(ticket);
        return new TicketOutputDto(ticket);
    }

    @Override
    public TicketOutputDto updateTicketById(String ticketId, TicketInputDto ticketInputDto){
        Ticket ticket = getTicket(ticketId);

        checkAndUpdateTicket(ticketInputDto, ticket);

        ticketRepository.save(ticket);
        return new TicketOutputDto(ticket);
    }

    @Override
    public List<TicketOutputDto> getAllTickets(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
        Page<Ticket> pageTickets = ticketRepository.findAll(pageRequest);

        List<TicketOutputDto> finalOutput = new ArrayList<>();
        for(Ticket ticket : pageTickets){
            finalOutput.add(new TicketOutputDto(ticket));
        }

        return finalOutput;
    }

    @Override
    public TicketOutputDto getTicketById(String ticketId){
        Ticket ticket = getTicket(ticketId);

        return new TicketOutputDto(ticket);
    }

    @Override
    public void deleteTicketById(String ticketId){
        ticketRepository.deleteById(ticketId);
    }





    //Métodos extraídos
    private Ticket getTicket(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->
                new EntityNotFoundException("El ticket con el id "+ ticketId +" no existe."));
        return ticket;
    }

    private static Ticket checkAndUpdateTicket(TicketInputDto ticketInputDto, Ticket ticket) {
        if(ticketInputDto.getPassengerId() != null && !ticketInputDto.getPassengerId().equals("")) {
            ticket.setPassengerId(ticketInputDto.getPassengerId());
        }
        if(ticketInputDto.getPassengerLastName() != null && !ticketInputDto.getPassengerLastName().equals("")) {
            ticket.setPassengerLastName(ticketInputDto.getPassengerLastName());
        }
        if(ticketInputDto.getPassengerEmail() != null && !ticketInputDto.getPassengerEmail().equals("")) {
            ticket.setPassengerEmail(ticketInputDto.getPassengerEmail());
        }
        if(ticketInputDto.getTripOrigin() != null && !ticketInputDto.getTripOrigin().equals("")) {
            ticket.setTripOrigin(ticketInputDto.getTripOrigin());
        }
        if(ticketInputDto.getTripDestination() != null && !ticketInputDto.getTripDestination().equals("")) {
            ticket.setTripDestination(ticketInputDto.getTripDestination());
        }
        if(ticketInputDto.getDepartureDate() != null){
            ticket.setDepartureDate(ticketInputDto.getDepartureDate());
        }
        if(ticketInputDto.getArrivalDate() != null){
            ticket.setArrivalDate(ticketInputDto.getArrivalDate());
        }
        return ticket;
    }
}
