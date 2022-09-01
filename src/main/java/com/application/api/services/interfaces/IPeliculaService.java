package com.application.api.services.interfaces;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;
import com.application.api.vo.PeliculaVO;

import java.util.List;

public interface IPeliculaService {

    public Pelicula getAllPeliculas();

    public Pelicula getPeliculaByName(String nombrePelicula);
    public Pelicula getPeliculaById(Integer idPelicula);

    public void validarInformacion(String nombrePelicula);

    public Pelicula guardarPelicula(PeliculaVO peliculaVO);

    public Pelicula guardarPelicula(String nombrePelicula, List<Actor> elenco,String productora,Integer duracionPelicula,Integer calificacion,Float precio);

    public boolean contieneActorEstrella(String nombrePelicula);

    public boolean productoraEsFamosa(String nombrePelicula);

    public boolean interestingCriteria(String nombrePelicula);

    public boolean estaInteresante(String nombrePelicula);


}
