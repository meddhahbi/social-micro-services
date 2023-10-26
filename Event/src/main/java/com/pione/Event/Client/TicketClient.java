package com.pione.Event.Client;

import com.pione.Event.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ticket-service", url = "${application.config.ticket-url}")
public interface TicketClient {
    @GetMapping("/event/{event-id}")
    List<Ticket> findAllTicketsByEvent(@PathVariable("event-id") Long eventId);
    @PostMapping("/ticket/{ticket-id}/assign/{event-id}")
    Ticket assignEventToTicket(
            @PathVariable("ticket-id") Long ticketId,
            @PathVariable("event-id") Long eventId
    );
}
