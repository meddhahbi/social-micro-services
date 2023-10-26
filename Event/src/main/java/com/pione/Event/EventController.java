package com.pione.Event;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/{eventId}/tickets")
    public ResponseEntity<EventTicket> getEventWithTickets(@PathVariable Long eventId) {
        EventTicket eventTicket = eventService.getEventWithTickets(eventId);

        if (eventTicket != null) {
            return ResponseEntity.ok(eventTicket);
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/tikcet/{event-id}")
    public ResponseEntity<EventTicket> getEventById(
            @PathVariable("event-id") long eventId){

        return ResponseEntity.ok(eventService.getEventWithTickets(eventId));
    }

}
