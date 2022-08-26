package com.application.api.services.interfaces;

import com.application.api.model.evento.Jugador;
import com.application.api.vo.JugadorVO;

public interface IJugadorService {

    //Jugador getJugadorById(Integer id);

    Jugador getJugadorByNombre(String nombre);

    Jugador guardarJugador(JugadorVO jugadorVO);

    Jugador guardarJugador(String nombre,boolean esEstrella,String nombreSeleccion);
}
