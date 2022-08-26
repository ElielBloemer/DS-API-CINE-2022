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
    @OneToMany
    private List<Actor> elenco;
    private String productora;
    private Integer duracionPelicula;

    public Pelicula(String nombrePelicula,List<Actor> elenco,String productora,Integer duracionPelicula){
        this.nombrePelicula=nombrePelicula;
        this.elenco=elenco;
        this.productora=productora;
        this.duracionPelicula=duracionPelicula;
    }
}
