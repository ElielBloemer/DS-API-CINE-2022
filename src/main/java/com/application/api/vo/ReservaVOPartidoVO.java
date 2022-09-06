package com.application.api.vo;

import com.application.api.model.combo.Combo;
import com.application.api.model.evento.Evento;
import com.application.api.model.evento.Partido;
import com.application.api.model.reserva.Reserva;

import java.time.LocalDateTime;

public class ReservaVOPartidoVO {

    public  String idReserva;
    public  Integer cantidadEntradas;

    public PartidoVO partido;
    //TODO - relacionar el COMBO con reserva
    //@OneToMany
    // private Combo combo;
    public  Float descuentoOtorgado;
    //TODO - el tipo de DESCUENTO
    public  String tipoDeDescuento;
    public  boolean reservaActiva;

    public float costoTotal;

    public ReservaVOPartidoVO(String idReserva, Integer cantidadEntradas, PartidoVO partidoVO, Combo combo, Float descuentoOtorgado, String tipoDeDescuento, boolean reservaActiva, Integer costoTotal) {
        this.idReserva = idReserva;
        this.cantidadEntradas = cantidadEntradas;
        this.partido = partidoVO;
        //this.combo = combo;
        this.descuentoOtorgado = descuentoOtorgado;
        this.tipoDeDescuento = tipoDeDescuento;
        this.reservaActiva = reservaActiva;
        this.costoTotal=costoTotal;
    }

    public ReservaVOPartidoVO(Reserva reserva,PartidoVO partidoVO){
        this.idReserva=reserva.getIdReserva();
        this.cantidadEntradas= reserva.getCantidadEntradas();
        this.partido=partidoVO;
        this.descuentoOtorgado= reserva.getDescuentoOtorgado();
        this.tipoDeDescuento=reserva.getTipoDeDescuento();
        this.reservaActiva=reserva.isReservaActiva();
        this.costoTotal=reserva.getCostoTotal();
    }

    public ReservaVOPartidoVO() {
    }
}
