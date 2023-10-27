package com.example.commentservice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private Integer blogId;
    private Integer userId;
}
