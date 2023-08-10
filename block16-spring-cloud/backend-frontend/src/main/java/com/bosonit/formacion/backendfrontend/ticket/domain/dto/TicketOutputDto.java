package com.bosonit.formacion.backendfrontend.ticket.domain.dto;

import com.bosonit.formacion.backendfrontend.ticket.domain.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketOutputDto {
    private String ticketId;
    private String passengerId;
    private String passengerLastName;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;

    public TicketOutputDto(Ticket ticket){
        this.ticketId = ticket.getTicketId();
        this.passengerId = ticket.getPassengerId();
        this.passengerLastName = ticket.getPassengerLastName();
        this.passengerEmail = ticket.getPassengerEmail();
        this.tripOrigin = ticket.getTripOrigin();
        this.tripDestination = ticket.getTripDestination();
        this.departureDate = ticket.getDepartureDate();
        this.arrivalDate = ticket.getArrivalDate();
    }
}
