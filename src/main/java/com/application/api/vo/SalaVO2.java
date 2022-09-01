package com.application.api.vo;

import com.application.api.model.evento.Sala;

public class SalaVO2 {

   // public Integer id;

    public String identificacionSala;

    public Integer asientosDisponibles;

    public Integer asientoReservados;

    public SalaVO2() {
    }

    public SalaVO2(Sala sala) {
        //this.id=sala.getId();
        this.identificacionSala = sala.getIdentificacionSala();
        this.asientosDisponibles = sala.getAsientosDisponibles();
        this.asientoReservados = sala.getAsientoReservados();
    }
}
