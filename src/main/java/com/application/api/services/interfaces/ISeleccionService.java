package com.application.api.services.interfaces;

import com.application.api.model.evento.Jugador;
import com.application.api.model.evento.Seleccion;
import com.application.api.vo.SeleccionVO;

import java.util.List;

public interface ISeleccionService {

    Seleccion getSeleccionPorNombre(String name);

    Seleccion guardarSeleccion(SeleccionVO selecioVO);
    Seleccion guardarSeleccion(String nombrePais, String continente, List<Jugador> jugadores, Integer mundialesGanados);

    ///Seleccion guardarSeleccion(String nombrePais, String continente, Integer mundialesGanados);

    boolean contieneJugadorEstrella(String nombreSelecion);

    boolean esMuyCampeona(String nombreSeleccion);

    public void validarInformacion(String nombrePais);

}
