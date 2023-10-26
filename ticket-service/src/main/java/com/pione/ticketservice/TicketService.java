package com.pione.ticketservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {


    @Autowired
    private TicketRepository repository;


    public void saveTicket(Ticket ticket){
        repository.save(ticket);
    }

    public List<Ticket> findAllTickets(){
        return repository.findAll();
    }

    public void deleteTicket(Integer id) {
        repository.deleteById(id);
    }
    public Ticket findTicketById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ticket> findAllTicketsByEvent(long eventId) {
        return repository.findAllByEventId(eventId);
    }

    public Ticket assignEventToTicket(Integer ticketId, Long eventId) {
        Ticket ticket = repository.findById(ticketId).orElse(null);

        if (ticket != null) {
            // Assurez-vous que la logique d'attribution de l'événement au ticket est correcte.
            ticket.setEventId(eventId);
            repository.save(ticket);
            return ticket;
        }

        return null;
    }


}
