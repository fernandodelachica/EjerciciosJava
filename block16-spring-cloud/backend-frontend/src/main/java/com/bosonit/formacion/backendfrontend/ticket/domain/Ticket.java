package com.bosonit.formacion.backendfrontend.ticket.domain;

import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    private String ticketId;
    private String passengerId;
    private String passengerLastName;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;


    public Ticket(TicketInputDto ticketInputDto){
        this.ticketId = UUID.randomUUID().toString();
        this.passengerId = ticketInputDto.getPassengerId();
        this.passengerLastName = ticketInputDto.getPassengerLastName();
        this.passengerEmail = ticketInputDto.getPassengerEmail();
        this.tripOrigin = ticketInputDto.getTripOrigin();
        this.tripDestination = ticketInputDto.getTripDestination();
        this.departureDate = ticketInputDto.getDepartureDate();
        this.arrivalDate = ticketInputDto.getArrivalDate();
    }

}
