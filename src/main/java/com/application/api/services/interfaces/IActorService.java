package com.application.api.services.interfaces;

import com.application.api.vo.ActorVO;
import com.application.api.model.evento.Actor;
import java.util.List;

public interface IActorService {

   public Actor getActorByNombre(String nombreActor);

   public Actor guardarActor(ActorVO actorVO);

   public Actor guardarActor(String nombre,String pelicula,boolean esEstrella);

   public List<Actor> getTodoLosActores(String NombrePelicula);

}
