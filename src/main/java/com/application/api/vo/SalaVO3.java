package com.application.api.vo;

import com.application.api.model.evento.Sala;

public class SalaVO3 {

   // public Integer id;

    public String identificacionSala;

    public Integer asientosDisponibles;

    public Integer asientoReservados;

    public SalaVO3() {
    }

    public SalaVO3(Sala sala) {
        //this.id=sala.getId();
        this.identificacionSala = sala.getIdentificacionSala();
        this.asientosDisponibles = sala.getAsientosDisponibles();
        this.asientoReservados = sala.getAsientoReservados();
    }
}
