package com.pione.ticketservice;


import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer eventId;
    private float price;
    private boolean status;
    private String type;
    private Integer nombre;
    @ElementCollection
    private List<Integer> participants;


}
