package com.application.api.services.descuentos;

import com.application.api.model.evento.Evento;
import com.application.api.model.usuario.Usuario;

public class DescuentoPremiumService extends Descuento{
    public DescuentoPremiumService(String nombreDescuento) {
        super(nombreDescuento);
    }

    @Override
    public boolean esValido(Usuario usuario, Evento evento) {
        return evento.getCalificacion()<=5;
    }

    @Override
    public float calcularDescuento(Usuario usuario, Evento evento) {
        return (float) (evento.getPrecio()*0.75);
    }
}
