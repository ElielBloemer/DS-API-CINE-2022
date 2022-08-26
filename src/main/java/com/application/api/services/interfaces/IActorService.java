package com.application.api.services.interfaces;

import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;

import java.util.List;

//Aqui e onde eu implemento uma interface para desenvolvcer a logica de negocios
public interface IActorService {

   //public Actor getActorById(Integer id);

   public Actor getActorByNombre(String nombreActor);

   public Actor guardarActor(ActorVO actorVO);

   public Actor guardarActor(String nombre,String pelicula,boolean esEstrella);

   public List<Actor> getTodoLosActores();
}
