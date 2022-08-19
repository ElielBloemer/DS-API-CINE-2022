package com.application.api.services;

import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;
import com.application.api.persistance.ActorRepository;
import com.application.api.services.interfaces.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

//Essa capa eu divido das capas de servicio com persistencia, etc.
@Service
public class ActorService implements IActorService {
    //Aqui e onde eu declaro a variavel para para injectar
    private ActorRepository actorRepository;

    //Aqui e onde eu traigo o ator do BD
    @Autowired
    public void setActorRepository(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }

    //Aqui disponibilizo o actor por id
    @Override
    public Actor getActorById(Integer id) {
        return actorRepository.findById(id).orElseThrow(()-> new NotFoundException("el Actor com id: "+ id +" no existe"));
    }

    @Override
    public Actor guardarActor(ActorVO actorVO) {
        return guardarActor(actorVO.nombre, actorVO.pelicula);
    }

    @Override
    public Actor guardarActor(String nombre, String pelicula) {
        String nombreMayusculo = nombre.toUpperCase();
        String peliculaMayuscula = pelicula.toUpperCase();
        return actorRepository.save(new Actor(nombreMayusculo,peliculaMayuscula));
    }

}
