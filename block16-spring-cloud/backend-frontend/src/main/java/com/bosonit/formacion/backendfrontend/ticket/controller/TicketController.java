package com.bosonit.formacion.backendfrontend.ticket.controller;

import com.bosonit.formacion.backendfrontend.feign.BackendFeign;
import com.bosonit.formacion.backendfrontend.ticket.application.TicketService;
import com.bosonit.formacion.backendfrontend.ticket.domain.dto.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("/ticket/{passengerId}/{tripId}")
    public TicketOutputDto generateTicket(@PathVariable String passengerId, @PathVariable String tripId){
        return ticketService.generateTicket(passengerId, tripId);
    }

    @GetMapping("/ticket")
    public List<TicketOutputDto> getAllTickets(
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize){
        return ticketService.getAllTickets(pageNumber, pageSize);
    }

    @GetMapping("/ticket/{id}")
    public TicketOutputDto getTicketById(@PathVariable String ticketId){
        return ticketService.getTicketById(ticketId);
    }

    //Prueba
    @GetMapping("/status")
    public String actuatorInfo(){
        return "La aplicación Backend-frontend se iniciado correctamente.";
    }

}
