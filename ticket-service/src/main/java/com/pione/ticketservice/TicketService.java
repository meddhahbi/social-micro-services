package com.pione.ticketservice;


import com.pione.ticketservice.Client.EventClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {


    @Autowired
    private TicketRepository repository;

    @Autowired
    private EventClient client;





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

    //    public FullTicketResponse findTicketsWithEvents(Integer eventId) {
//        Event event = client.getEventById(eventId); // Use the EventClient to retrieve the event by ID
//
//        if (event != null) {
//            // Once you have the event, you can find the associated ticket
//            Ticket ticket = repository.findByEventId(eventId);
//
//            if (ticket != null) {
//                // Return the ticket and the event
//                return FullTicketResponse.builder()
//                        .ticket(ticket)
//                        .event(event) // Convert the event to a list
//                        .build();
//            } else {
//                // Handle the case where there is no ticket associated with the event
//                return null;
//            }
//        } else {
//            // Handle the case where the event with the provided ID doesn't exist
//            return null;
//        }
//    }
    public List<FullTicketResponse> findAllTicketsWithEvents() {
        List<Ticket> tickets = repository.findAll();
        List<FullTicketResponse> responses = new ArrayList<>();

        for (Ticket ticket : tickets) {
            Integer eventId = ticket.getEventId(); // Assuming eventId is a field in the Ticket entity

            Event event = client.getEventById(eventId);

            if (event != null) {
                responses.add(FullTicketResponse.builder()
                        .ticket(ticket)
                        .event(event)
                        .build());
            }
        }

        return responses;
    }
    public List<Ticket> findTicketsByUserId(Integer userId) {
        // Retrieve all tickets from the repository
        List<Ticket> allTickets = repository.findAll();

        // Filter tickets where the userId is in the participants list
        List<Ticket> ticketsWithUserId = allTickets.stream()
                .filter(ticket -> ticket.getParticipants() != null && ticket.getParticipants().contains(userId))
                .collect(Collectors.toList());

        return ticketsWithUserId;
    }
    public List<FullTicketResponse> findAllMyTickets(Integer userId) {
        List<Ticket> tickets = findTicketsByUserId(userId);
        List<FullTicketResponse> responses = new ArrayList<>();

        for (Ticket ticket : tickets) {
            Integer eventId = ticket.getEventId(); // Assuming eventId is a field in the Ticket entity

            Event event = client.getEventById(eventId);

            if (event != null) {
                responses.add(FullTicketResponse.builder()
                        .ticket(ticket)
                        .event(event)
                        .build());
            }
        }

        return responses;
    }
    public void participate(Integer ticketId, Integer userId) {
        Ticket ticket = findTicketById(ticketId);

        if (ticket != null && ticket.getNombre() > 0) {
            List<Integer> participants = ticket.getParticipants();
            if (participants == null) {
                participants = new ArrayList<>();
            }
            participants.add(userId);
            ticket.setParticipants(participants);

            // Decrease the available tickets by 1
            int newNombre = ticket.getNombre() - 1;
            ticket.setNombre(newNombre);

            // If the available tickets reach 0, update the status
            if (newNombre == 0) {
                ticket.setStatus(true);
            }

            // Save the updated ticket back to the database
            saveTicket(ticket);
        }
    }
    public FullTicketResponse findTicketWithEventById(Integer ticketId) {
        Ticket ticket = repository.findById(ticketId).orElse(null); // Adjust this to your repository method

        if (ticket != null) {
            Integer eventId = ticket.getEventId(); // Assuming eventId is a field in the Ticket entity
            Event event = client.getEventById(eventId);

            if (event != null) {
                return FullTicketResponse.builder()
                        .ticket(ticket)
                        .event(event)
                        .build();
            }
        }

        return null; // Ticket or event not found
    }

    public List<Ticket> findAllTicketsByEvent(Integer eventId) {
        return repository.findAllByEventId(eventId);
    }

    public Ticket assignEventToTicket(Integer ticketId, Integer eventId) {
        Ticket ticket = repository.findById(ticketId).orElse(null);

        if (ticket != null) {
            // Assurez-vous que la logique d'attribution de l'événement au ticket est correcte.
            ticket.setEventId(eventId);
            repository.save(ticket);
            return ticket;
        }

        return null;
    }
    public List<Event> findAllEvents(){
        return client.getAllEvents();
    }




}