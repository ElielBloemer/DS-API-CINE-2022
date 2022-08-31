package com.application.api.vo;

import com.application.api.model.evento.Sala;

public class SalaVO {

    public Integer id;

    public String identifacionSala;

    public Integer asientosDisponibles;

    public Integer asientoReservados;

    public SalaVO() {
    }

    public SalaVO(Sala sala) {
        this.id=sala.getId();
        this.identifacionSala = sala.getIdentificacionSala();
        this.asientosDisponibles = sala.getAsientosDisponibles();
        this.asientoReservados = sala.getAsientoReservados();
    }

    /*public SalaVO(String identifacionSala,Integer asientosDisponibles,Integer asientoReservados) {
        this.identifacionSala = identifacionSala;
        this.asientosDisponibles = asientosDisponibles;
        this.asientoReservados = asientoReservados;
    }*/
}
