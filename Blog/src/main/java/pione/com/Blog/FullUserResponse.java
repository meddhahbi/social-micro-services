package pione.com.Blog;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FullUserResponse {
    private String titre;

    List<user> users;

}
