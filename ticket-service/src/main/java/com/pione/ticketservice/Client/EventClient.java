package com.pione.ticketservice.Client;

import com.pione.ticketservice.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "events",url = "${application.config.event-url}")
public interface EventClient {

    @GetMapping
    public List<Event> getAllEvents();
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Integer id);


}
