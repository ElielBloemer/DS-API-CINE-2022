package com.application.api.vo;

import com.application.api.model.evento.Actor;

public class ActorVO {
 
    public Integer id;
    public String nombre;

    public String pelicula;

    public ActorVO() {
    }

    public ActorVO(Actor actor) {
        this.id = actor.getId();
        this.nombre = actor.getNombre();
        this.pelicula = actor.getPelicula();
    }
}
