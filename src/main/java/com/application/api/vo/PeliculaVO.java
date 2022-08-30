package com.application.api.vo;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class PeliculaVO {

    public Integer idPelicula;
    public String nombrePelicula;
   // public List<Actor> elenco;
    public String productora;
    public Integer duracionPeliculaMinutosPelicula;
    //public Integer puntuacionEvento;

    public PeliculaVO(String nombrePelicula,List<Actor> elenco,String productora,Integer duracionPelicula){
        this.nombrePelicula=nombrePelicula;
        //this.elenco=elenco;
        this.productora=productora;
        this.duracionPeliculaMinutosPelicula=duracionPelicula;
        //this.puntuacionEvento=puntuacionPelicula;
    }

    public PeliculaVO(Pelicula pelicula){
        this.idPelicula=pelicula.getIdPelicula();
        this.nombrePelicula=pelicula.getNombrePelicula();
        this.duracionPeliculaMinutosPelicula=pelicula.getDuracionPelicula();
        //this.puntuacionEvento=pelicula.getPuntuacionEvento();
        //this.elenco=pelicula.getElenco();
        this.productora=pelicula.getProductora();
    }
}
