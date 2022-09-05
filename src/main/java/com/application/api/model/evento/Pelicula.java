package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Pelicula extends Evento{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer idPelicula;*/
    private String nombrePelicula;
    @OneToMany(mappedBy = "pelicula")
    @JsonIgnore
    //@JoinColumn(referencedColumnName = "nombreActor")
    private List<Actor> elenco;
    private String productora;
    private Integer duracionPelicula;
    //private Integer puntuacionEvento;

    public Pelicula(String nombrePelicula, List<Actor> elenco, String productora, Integer duracionPelicula, Integer calificacion, Float precio, LocalDateTime fechaEvento,String idSala,boolean estaActivo){
        //super(calificacion,precio,sala);
        super(calificacion,precio,fechaEvento,idSala,estaActivo);
        this.nombrePelicula=nombrePelicula;
        this.elenco=elenco;
        this.productora=productora;
        this.duracionPelicula=duracionPelicula;
        //this.puntuacionEvento=puntuacionEvento;
    }
}
