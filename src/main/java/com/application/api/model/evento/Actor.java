package com.application.api.model.evento;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String pelicula;

    public Actor(String nombre, String pelicula) {
        this.nombre = nombre;
        this.pelicula = pelicula;
    }

}