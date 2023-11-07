package com.pione.ticketservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tickets")
public class TicketController {


    @Autowired
    private TicketService service;

    private final PDFGeneratorService pdfGeneratorService;

    public TicketController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate/{ticketId}")
    public void generatePDF(HttpServletResponse response,@PathVariable Integer ticketId) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        FullTicketResponse ticket = service.findTicketWithEventById(ticketId);

        this.pdfGeneratorService.export(response,ticket);
    }
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
    @GetMapping("/events")
    public ResponseEntity<List<Event>> findAllTEvents(){
        return ResponseEntity.ok(service.findAllEvents());
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
    @GetMapping("/allWithEvents")
    public ResponseEntity<List<FullTicketResponse>> findAllTicketsWithEvents() {
        List<FullTicketResponse> responses = service.findAllTicketsWithEvents();
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/myTickets/{userId}")
    public ResponseEntity<List<FullTicketResponse>> findAllMyTickets(@PathVariable Integer userId) {
        List<FullTicketResponse> responses = service.findAllMyTickets(userId);
        return ResponseEntity.ok(responses);
    }

    // Define a new endpoint for ticket participation
    @PostMapping("/{ticketId}/participate/{userId}")
    public ResponseEntity<String> participate(
            @PathVariable Integer ticketId,
            @PathVariable Integer userId
    ) {
        service.participate(ticketId, userId);
        return ResponseEntity.ok("User participated in the event.");
    }


    @GetMapping("/event/{event-id}")
    public ResponseEntity<List<Ticket>>findAllTickets(
            @PathVariable("event-id") Integer eventId
    ){
        return ResponseEntity.ok(service.findAllTicketsByEvent(eventId));
    }


    @PostMapping("/{ticket-id}/assign/{event-id}")
    public ResponseEntity<Ticket> assignEventToTicket(
            @PathVariable("ticket-id") Integer ticketId,
            @PathVariable("event-id") Integer eventId
    ) {
        Ticket assignedTicket = service.assignEventToTicket(ticketId, eventId);

        if (assignedTicket != null) {
            return ResponseEntity.ok(assignedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
