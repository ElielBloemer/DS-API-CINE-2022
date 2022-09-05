package com.application.api.vo;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;

import java.util.List;

public class PeliculaVO2 {

    public Integer idPelicula;
    public String nombrePelicula;
    public List<Actor> elenco;
    public String productora;
    public Integer duracionPelicula;
    public String idSala;

    /*public PeliculaVO2(String nombrePelicula,List<Actor> elenco,String productora,Integer duracionPelicula){
        this.nombrePelicula=nombrePelicula;
        this.elenco=elenco;
        this.productora=productora;
        this.duracionPelicula=duracionPelicula;
        //this.puntuacionEvento=puntuacionPelicula;
    }*/

    public PeliculaVO2(Pelicula pelicula){
        this.idPelicula=pelicula.getId();
        this.nombrePelicula=pelicula.getNombrePelicula();
        this.duracionPelicula=pelicula.getDuracionPelicula();
        //this.puntuacionEvento=pelicula.getPuntuacionEvento();
        this.elenco=pelicula.getElenco();
        this.productora=pelicula.getProductora();
        this.idSala=pelicula.getIdSala();
    }
}
