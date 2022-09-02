package com.application.api.vo;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PeliculaVO {

    public Integer idPelicula;
    public String nombrePelicula;
   // public List<Actor> elenco;
    public String productora;
    public Integer duracionPeliculaMinutosPelicula;
    public Integer calificacionEvento;
    public Float precioEvento;
    public LocalDateTime fechaEvento;
    /*public String identificacionSala;
    //public Integer puntuacionEvento;*/

    public PeliculaVO(String nombrePelicula, String productora, Integer duracionPelicula, Integer calificacionEvento,Float precioEvento,LocalDateTime fechaEvento){
        this.nombrePelicula=nombrePelicula;
        this.productora=productora;
        this.duracionPeliculaMinutosPelicula=duracionPelicula;
        this.calificacionEvento=calificacionEvento;
        this.precioEvento=precioEvento;
        this.fechaEvento=fechaEvento;
    }

    public PeliculaVO(Pelicula pelicula){
        this.idPelicula=pelicula.getId();
        this.nombrePelicula=pelicula.getNombrePelicula();
        this.duracionPeliculaMinutosPelicula=pelicula.getDuracionPelicula();
        this.productora=pelicula.getProductora();
        this.calificacionEvento=pelicula.getCalificacion();
        this.precioEvento=pelicula.getPrecio();
        this.fechaEvento=pelicula.getFechaEvento();
    }
}
