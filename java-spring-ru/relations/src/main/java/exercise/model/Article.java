package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String name;
    @Lob
    private String body;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
// END
