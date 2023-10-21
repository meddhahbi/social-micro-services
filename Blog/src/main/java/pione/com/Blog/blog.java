package pione.com.Blog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class blog {
    @Id
    @GeneratedValue
    private Integer id;
    private String titre;
    private String description;
    private String auteur;
    private Integer categoryId;
    private Integer userId;


}
