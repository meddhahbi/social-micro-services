package com.pione.Event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventTicket {
    private String eventName;
    List<Ticket> tickets;


}
