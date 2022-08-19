package com.application.api.services.interfaces;

import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;

//Aqui e onde eu implemento uma interface
public interface IActorService {

   Actor getActorById(Integer id);

   Actor guardarActor(ActorVO actorVO);

   Actor guardarActor(String nombre,String pelicula);
}
