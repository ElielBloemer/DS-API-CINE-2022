package com.application.api.vo;

import com.application.api.model.evento.Jugador;

public class JugadorVO {

    public Integer id;

    public String nombre;

    public JugadorVO() {
    }

    public JugadorVO(Jugador jugador){
        this.id = jugador.getId();
        this.nombre = jugador.getNombre();

    }

}
