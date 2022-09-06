package com.application.api.vo;

import com.application.api.model.combo.Combo;
import com.application.api.model.evento.Evento;
import com.application.api.model.reserva.Reserva;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
public class ReservaVO {

    public  String idReserva;
    public  Integer cantidadEntradas;

    public  Evento evento;
    //TODO - relacionar el COMBO con reserva
    //@OneToMany
    // private Combo combo;
    public  Float descuentoOtorgado;
    //TODO - el tipo de DESCUENTO
    public  String tipoDeDescuento;
    public  boolean reservaActiva;

    public float costoTotal;

    public ReservaVO(String idReserva, Integer cantidadEntradas, Evento evento, Combo combo, Float descuentoOtorgado, String tipoDeDescuento, boolean reservaActiva,Integer costoTotal) {
        this.idReserva = idReserva;
        this.cantidadEntradas = cantidadEntradas;
        this.evento = evento;
        //this.combo = combo;
        this.descuentoOtorgado = descuentoOtorgado;
        this.tipoDeDescuento = tipoDeDescuento;
        this.reservaActiva = reservaActiva;
        this.costoTotal=costoTotal;
    }

    public ReservaVO(Reserva reserva){
        this.idReserva=reserva.getIdReserva();
        this.cantidadEntradas= reserva.getCantidadEntradas();
        this.evento=reserva.getEvento();
        this.descuentoOtorgado= reserva.getDescuentoOtorgado();
        this.tipoDeDescuento=reserva.getTipoDeDescuento();
        this.reservaActiva=isReservaActiva();
        this.costoTotal=reserva.getCostoTotal();
    }

    public ReservaVO() {
    }
}
