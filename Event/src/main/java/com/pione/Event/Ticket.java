package com.pione.Event;

import lombok.*;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    private Long id;
    private Long eventId;
    private float price;
    private boolean status;
    private String type;


    // Additional fields and methods can be added here
    // For example, you can add getters and setters for the new fields.
}
