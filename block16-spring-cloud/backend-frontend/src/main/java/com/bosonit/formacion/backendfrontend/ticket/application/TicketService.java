package com.bosonit.formacion.backendfrontend.ticket.application;

import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketInputDto;
import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketOutputDto;

import java.util.List;

public interface TicketService {

    TicketOutputDto generateTicket(String passengerId, String tripId);

    TicketOutputDto updateTicketById(String ticketId, TicketInputDto ticketInputDto);

    List<TicketOutputDto> getAllTickets(int pageNumber, int pageSize);

    TicketOutputDto getTicketById(String ticketId);

    void deleteTicketById(String ticketId);

}
