package com.pione.ticketservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findByEventId(Integer eventId);

    List<Ticket> findAllByEventId(long eventId);
}
