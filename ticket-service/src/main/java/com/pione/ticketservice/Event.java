package com.pione.ticketservice;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

private Integer id;
    private String eventName;
    private Date startDate;
    private Date endDate;

    private String place;




}
