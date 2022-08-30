package com.application.api.services;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Pelicula;
import com.application.api.model.evento.Seleccion;
import com.application.api.persistance.ActorRepository;
import com.application.api.persistance.PeliculaRepository;
import com.application.api.services.interfaces.IPeliculaService;
import com.application.api.vo.PeliculaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class PeliculaService implements IPeliculaService {
    private PeliculaRepository peliculaRepository;
    private ActorRepository actorRepository;

    @Autowired
    public void setPeliculaService(PeliculaRepository peliculaRepository){
        this.peliculaRepository=peliculaRepository;
    }
    @Autowired
    public void setActorRepository(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }
    
    @Override
    public Pelicula getPeliculaByName(String nombrePelicula) {
        Pelicula pelicula=peliculaRepository.findByNombrePelicula(nombrePelicula);
        peliculaExiste(pelicula,nombrePelicula.toUpperCase());
        List<Actor> actores=actorRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        pelicula.setElenco(existeActoresEnEsPelicula(actores));
        return pelicula;
    }

    @Override
    public void validarInformacion(String nombrePelicula) {
        if(nombrePelicula.equals("STRING")){
            throw new NotFoundException("Nombre INVALIDO para la pelicula");
        }
    }

    @Override
    public Pelicula guardarPelicula(PeliculaVO peliculaVO) {
        return guardarPelicula(peliculaVO.nombrePelicula,null,peliculaVO.productora, peliculaVO.duracionPeliculaMinutosPelicula, peliculaVO.puntuacionEvento);
    }

    @Override
    public Pelicula guardarPelicula(String nombrePelicula, List<Actor> elenco, String productora, Integer duracionPelicula, Integer puntuacionPelicula) {
        Pelicula nuevaPelicula=peliculaRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        estaEnElSistema(nuevaPelicula,nombrePelicula);
        return peliculaRepository.save(new Pelicula(nombrePelicula.toUpperCase(),elenco,productora.toUpperCase(),duracionPelicula,puntuacionPelicula));
    }

    @Override
    public boolean contieneActorEstrella(String nombrePelicula) {
        Pelicula pelicula=getPeliculaByName(nombrePelicula.toUpperCase());
        List<Actor> actores=pelicula.getElenco();
        for (Actor actor: actores) {
            if(actor.isEsEstrella()){
                return true;
            }
        }
        return false;
    }

    private List<Actor> existeActoresEnEsPelicula(List<Actor> actores) {
        return actores;
    }

    public void peliculaExiste(Pelicula pelicula,String nombrePelicula){
        if(pelicula==null){
            throw new NotFoundException("La Pelicula com nombre "+ nombrePelicula + " NO existe en el sistema");
        }
    }

    public void estaEnElSistema(Pelicula pelicula, String nombre) {
        if (pelicula != null) {
            throw new NotFoundException("la pelicula " + nombre.toUpperCase() + " ya esta en el sistema!");
        }
    }
}
