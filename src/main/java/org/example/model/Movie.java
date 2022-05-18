package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDate releaseDate;
    @OneToOne
    private Badge badge;

    public Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
