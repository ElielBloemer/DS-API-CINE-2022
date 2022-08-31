package com.application.api.services.validations;

import com.application.api.model.evento.Seleccion;
import org.webjars.NotFoundException;

public class Validacion{

    public void getValidacion(Object object, String nombreSeleccion,String mensajeAMostrarPorConsola) {
        if(object==null){
            throw new NullPointerException(nombreSeleccion + mensajeAMostrarPorConsola);
        }
    }
}
