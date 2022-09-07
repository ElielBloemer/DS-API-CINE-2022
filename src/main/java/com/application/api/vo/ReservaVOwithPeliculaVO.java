package com.application.api.vo;

import com.application.api.model.combo.Combo;
import com.application.api.model.reserva.Reserva;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaVOwithPeliculaVO {

    public  String idReserva;
    public  Integer cantidadEntradas;

    public PeliculaVO pelicula;
    //TODO - relacionar el COMBO con reserva
    //@OneToMany
    // private Combo combo;
    public  Float descuentoOtorgado;
    //TODO - el tipo de DESCUENTO
    public  String tipoDeDescuento;
    public  boolean reservaActiva;

    public float costoTotal;

    public ReservaVOwithPeliculaVO(String idReserva, Integer cantidadEntradas, PeliculaVO peliculaVO, Combo combo, Float descuentoOtorgado, String tipoDeDescuento, boolean reservaActiva, Integer costoTotal) {
        this.idReserva = idReserva;
        this.cantidadEntradas = cantidadEntradas;
        this.pelicula = peliculaVO;
        //this.combo = combo;
        this.descuentoOtorgado = descuentoOtorgado;
        this.tipoDeDescuento = tipoDeDescuento;
        this.reservaActiva = reservaActiva;
        this.costoTotal=costoTotal;
    }

    public ReservaVOwithPeliculaVO(Reserva reserva, PeliculaVO peliculaVO){
        this.idReserva=reserva.getIdReserva();
        this.cantidadEntradas= reserva.getCantidadEntradas();
        this.pelicula=peliculaVO;
        this.descuentoOtorgado= reserva.getDescuentoOtorgado();
        this.tipoDeDescuento=reserva.getTipoDeDescuento();
        this.reservaActiva=reserva.isReservaActiva();
        this.costoTotal=reserva.getCostoTotal();
    }

    public ReservaVOwithPeliculaVO() {
    }
}
