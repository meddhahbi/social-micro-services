package com.example.commentservice;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
