package com.application.api.vo;

import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;

public class JugadorVO {

    public Integer id;

    public String nombre;
    public boolean esEstrella;
    public String nombreSeleccion;
    public JugadorVO() {
    }

    public JugadorVO(Jugador jugador){
        this.id = jugador.getId();
        this.nombre = jugador.getNombreJugador();
        this.esEstrella = jugador.isEsEstrella();
        this.nombreSeleccion= jugador.getSeleccion().getNombrePais();
    }

    public JugadorVO(String nombreJugador,boolean esEstrella,String nombreSeleccion){
        this.nombre = nombreJugador;
        this.esEstrella = esEstrella;
        this.nombreSeleccion= nombreSeleccion;
    }

}
