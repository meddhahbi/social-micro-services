package com.pione.ticketservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findByEventId(Integer eventId);

    List<Ticket> findAllByEventId(Integer eventId);
}
