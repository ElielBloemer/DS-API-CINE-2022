package com.application.api.services.evento;

import com.application.api.model.evento.Pelicula;
import com.application.api.persistance.PeliculaRepository;
import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;
import com.application.api.persistance.ActorRepository;
import com.application.api.services.interfaces.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;

//Essa capa eu divido das capas de servicio com persistencia, etc.
@Service
public class ActorService implements IActorService {
    //Aqui é onde eu declaro a variavel para para injectar
    private ActorRepository actorRepository;
    private PeliculaRepository peliculaRepository;

    @Autowired
    public void setPeliculaRepository(PeliculaRepository peliculaRepository){
        this.peliculaRepository=peliculaRepository;
    }

    //Aqui e onde eu traigo o ator do BD
    @Autowired
    public void setActorRepository(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }

    @Override
    public Actor getActorByNombre(String nombreActor) {
        Actor actor=actorRepository.findByNombreActor(nombreActor);
       // actorExiste(actor,nombreActor);
        validacion(actor,nombreActor," NO figura en el sistema. GRACIAS");
        return actor;
    }

    @Override
    public Actor guardarActor(ActorVO actorVO) {
        return guardarActor(actorVO.nombreActor, actorVO.pelicula,actorVO.esEstrella);
    }

    @Override
    public Actor guardarActor(String nombre, String nombrePelicula,boolean esEstrella) {
        Pelicula pelicula = peliculaRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        validacion(pelicula,nombrePelicula," NO figura en el sistema, POR FAVOR inserte la pelicula PRIMERO.");
        Actor actor = actorRepository.findByNombreActor(nombre.toUpperCase());
        estaEnElSistemaElActor(actor,nombre);
        return actorRepository.save(new Actor(nombre.toUpperCase(),esEstrella,pelicula));
    }

    private void validacion(Object object, String nombrePelicula,String mensajeAMostrarPorConsola) {
        if(object==null){
            throw new NullPointerException(nombrePelicula +mensajeAMostrarPorConsola);
        }
    }

    private void estaEnElSistemaElActor(Actor byNombreActor, String nombre) {
        if(byNombreActor!=null){
            throw new NullPointerException(nombre +" : ya esta en el sistema. ");
        }
    }

    @Override
    public List<Actor> getTodoLosActores(String nombrePelicula) {
        //List<Actor> actores=actorRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        Pelicula pelicula=peliculaRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        return pelicula.getElenco();
    }

}
