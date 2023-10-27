package com.pione.Event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String eventName;
    private Date startDate;
    private Date endDate;
    private Integer userId;
    private String place;

    // Additional fields and methods can be added here
    // For example, you can add getters and setters for the new fields.
}
