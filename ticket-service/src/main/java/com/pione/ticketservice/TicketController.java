package com.pione.ticketservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tickets")
public class TicketController {


    @Autowired
    private TicketService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody Ticket ticket){
        service.saveTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket saved successfully!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTicket(@PathVariable Integer id){
        service.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket deleted successfully!");
    }


    @GetMapping
    public ResponseEntity<List<Ticket>> findAllTickets(){
        return ResponseEntity.ok(service.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findTicketById(@PathVariable Integer id) {
        Ticket ticket = service.findTicketById(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Integer id, @RequestBody Ticket updatedTicket) {
        Ticket existingTicket = service.findTicketById(id);
        if (existingTicket != null) {
            updatedTicket.setId(id); // Make sure the ID matches
            service.saveTicket(updatedTicket);
            return ResponseEntity.ok("Ticket with ID " + id + " updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/event/{event-id}")
    public ResponseEntity<List<Ticket>>findAllTickets(
            @PathVariable("event-id") long eventId
    ){
        return ResponseEntity.ok(service.findAllTicketsByEvent(eventId));
    }


    @PostMapping("/{ticket-id}/assign/{event-id}")
    public ResponseEntity<Ticket> assignEventToTicket(
            @PathVariable("ticket-id") Integer ticketId,
            @PathVariable("event-id") Long eventId
    ) {
        Ticket assignedTicket = service.assignEventToTicket(ticketId, eventId);

        if (assignedTicket != null) {
            return ResponseEntity.ok(assignedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
