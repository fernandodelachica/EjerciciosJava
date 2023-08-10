package com.bosonit.formacion.backendfrontend.ticket.repository;

import com.bosonit.formacion.backendfrontend.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
