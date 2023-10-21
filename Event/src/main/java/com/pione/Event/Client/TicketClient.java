package com.pione.Event.Client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ticket-service",url = "${application.config.ticket-url}")

public interface TicketClient {
}
