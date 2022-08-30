package com.application.api.services;

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

    //Aqui e onde eu traigo o ator do BD
    @Autowired
    public void setActorRepository(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }

    @Override
    public Actor getActorByNombre(String nombreActor) {
        Actor actor=actorRepository.findByNombreActor(nombreActor);
        actorExiste(actor,nombreActor);
        return actor;
    }

    private void actorExiste(Actor actor, String nombreActor) {
        if(actor==null){
            throw new NotFoundException(nombreActor + " NO esta en el sistema.");
        }
    }

    @Override
    public Actor guardarActor(ActorVO actorVO) {
        return guardarActor(actorVO.nombreActor, actorVO.pelicula,actorVO.esEstrella);
    }

    @Override
    public Actor guardarActor(String nombre, String pelicula,boolean esEstrella) {
        String nombreMayusculo = nombre.toUpperCase();
        String peliculaMayuscula = pelicula.toUpperCase();
        estaEnElSistemaElActor(actorRepository.findByNombreActor(nombre.toUpperCase()),nombre);
        return actorRepository.save(new Actor(nombreMayusculo,peliculaMayuscula,esEstrella));
    }

    private void estaEnElSistemaElActor(Actor byNombreActor, String nombre) {
        if(byNombreActor!=null){
            throw new NullPointerException(nombre +" : ya esta en el sistema. ");
        }
    }

    @Override
    public List<Actor> getTodoLosActores(String nombrePelicula) {
        List<Actor> actores=actorRepository.findByNombrePelicula(nombrePelicula.toUpperCase());
        return null;
    }

    /*@Override
    public List<Actor> getTodoLosActores() {
        //return (List<Actor>) actorRepository.findAll();
        return null;
    }*/

}
