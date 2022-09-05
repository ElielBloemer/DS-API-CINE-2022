package com.application.api.services.descuentos;

import com.application.api.model.evento.Evento;
import com.application.api.model.usuario.Usuario;

public class DescuentoEstandarService extends Descuento {
    public DescuentoEstandarService(String nombreDescuento) {
        super(nombreDescuento);
    }

    @Override
    public boolean esValido(Usuario usuario, Evento evento) {
        return false;
    }

    @Override
    public float calcularDescuento(Usuario usuario, Evento evento) {
        return 0;
    }
}
