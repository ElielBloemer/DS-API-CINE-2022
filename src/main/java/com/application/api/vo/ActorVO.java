package com.application.api.vo;

import com.application.api.model.evento.Actor;

public class ActorVO {
 
    public Integer id;
    public String nombreActor;
    public String pelicula;
    public boolean esEstrella;
    public ActorVO() {
    }
    public ActorVO(Actor actor) {
        this.id = actor.getId();
        this.nombreActor = actor.getNombreActor();
        this.pelicula = actor.getPelicula();
        this.esEstrella=actor.isEsEstrella();
    }

    public ActorVO(String nombreActor,String pelicula,boolean esEstrella){
        this.nombreActor=nombreActor;
        this.pelicula=pelicula;
        this.esEstrella=esEstrella;
    }
}
