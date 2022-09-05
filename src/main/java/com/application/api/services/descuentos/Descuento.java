package com.application.api.services.descuentos;

import com.application.api.model.evento.Evento;
import com.application.api.model.usuario.Usuario;
import com.application.api.services.reserva.ReservaService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Descuento {

    private String nombreDescuento;

    public abstract boolean esValido(Usuario usuario, Evento evento);

    public abstract float calcularDescuento(Usuario usuario,Evento evento);

    public Descuento(String nombreDescuento){
        this.nombreDescuento=nombreDescuento;
    }
}
