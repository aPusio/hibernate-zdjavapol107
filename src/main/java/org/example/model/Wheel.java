package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "KOLO")
public class Wheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "ROZMIAR")
    private Integer size;
    private String type;

    public Wheel(String name, Integer size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
}
