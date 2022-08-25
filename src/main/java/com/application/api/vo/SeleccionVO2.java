package com.application.api.vo;

import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;

import java.util.List;

public class SeleccionVO2 {
    public Integer id;
    public String nombrePais;
    public String continente;
    public List<Jugador> jugadorTitulares;
    public Integer mundialesGanados;

    public SeleccionVO2(Seleccion seleccion) {
        this.id= seleccion.getId();
        this.nombrePais= seleccion.getNombrePais();
        this.continente= seleccion.getContinente();
        this.jugadorTitulares=seleccion.getJugadorTitulares();
        this.mundialesGanados=seleccion.getMundialesGanados();
    }

    public SeleccionVO2(String nombrePais, String continente, List<Jugador> jugadores, Integer mundialesGanados){
        this.nombrePais = nombrePais;
        this.continente = continente;
        this.jugadorTitulares = jugadores;
        this.mundialesGanados = mundialesGanados;
    }
}
