package com.pione.ticketservice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullTicketResponse {

  private Ticket ticket;

   private Event event;


}
