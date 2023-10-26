package com.pione.Event;

import com.pione.Event.Client.TicketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;
    private TicketClient ticketClient;

    @Autowired
    public EventService(EventRepository eventRepository, TicketClient ticketClient) {
        this.eventRepository = eventRepository;
        this.ticketClient = ticketClient;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(id).orElse(null);

        if (existingEvent != null) {
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setStartDate(updatedEvent.getStartDate());
            existingEvent.setEndDate(updatedEvent.getEndDate());
            return eventRepository.save(existingEvent);
        }

        return null;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }





    public EventTicket getEventWithTickets(Long eventId) {
        var event = eventRepository.findById(eventId)
                .orElse(Event.builder()
                        .eventName("NOT_FOUND")
                        .build());

        var tickets = ticketClient.findAllTicketsByEvent(eventId);

        return EventTicket.builder()
                .eventName(event.getEventName()) // Utilisez l'instance 'event' ici
                .tickets(tickets) // Assurez-vous que le nom de l'attribut est correct
                .build();
    }
    public Ticket assignEventToTicket(Long eventId, Long ticketId) {
        var ticket = ticketClient.assignEventToTicket(ticketId, eventId);

        // Assurez-vous que la logique de traitement du ticket est correcte.

        return ticket;
    }


}
