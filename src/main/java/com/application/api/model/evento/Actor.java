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
    private String nombreActor;
    private String pelicula;

    private boolean esEstrella;
    public Actor(String nombreActor, String pelicula,boolean esEstrella) {
        this.nombreActor = nombreActor;
        this.pelicula = pelicula;
        this.esEstrella=esEstrella;
    }

}