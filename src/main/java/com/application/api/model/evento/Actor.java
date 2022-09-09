package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pelicula pelicula;
    private boolean esEstrella;
    public Actor(String nombreActor,boolean esEstrella,Pelicula pelicula) {
        this.nombreActor = nombreActor;
        this.pelicula = pelicula;
        this.esEstrella=esEstrella;
    }

}