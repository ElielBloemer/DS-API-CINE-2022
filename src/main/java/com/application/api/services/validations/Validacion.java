package com.application.api.services.validations;

import com.application.api.model.evento.Sala;
import com.application.api.model.evento.Seleccion;
import org.webjars.NotFoundException;

public class Validacion{

    public void getValidacion(Object object, String nombreobject,String mensajeAMostrarPorConsola) {
        if(object==null){
            throw new NullPointerException(nombreobject + mensajeAMostrarPorConsola);
        }
    }

    public void getValidacionSiEstaEnElSistema(Object object, String nombreobject,String mensajeAMostrarPorConsola) {
        if(object!=null){
            throw new NullPointerException(nombreobject + mensajeAMostrarPorConsola);
        }
    }

    public void estaSalaOcupada(Sala sala){
        if(sala.isTieneEventoAsignado())
            throw new RuntimeException("SALA YA CONTIENE UN EVENTO ASIGNADO! PLEASE ASIGNE A OTRO SALA VACIA.");
    }
}
