package com.application.api.model.evento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPelicula;
    private String nombrePelicula;
    @OneToMany//(mappedBy = "nombreActor")
    //@JoinColumn(referencedColumnName = "nombreActor")
    private List<Actor> elenco;
    private String productora;
    private Integer duracionPelicula;

    private Integer puntuacionEvento;

    public Pelicula(String nombrePelicula,List<Actor> elenco,String productora,Integer duracionPelicula,Integer puntuacionEvento){
        this.nombrePelicula=nombrePelicula;
        this.elenco=elenco;
        this.productora=productora;
        this.duracionPelicula=duracionPelicula;
        this.puntuacionEvento=puntuacionEvento;
    }
}
