package com.application.api.vo;

import com.application.api.model.evento.Evento;
import com.application.api.model.evento.Sala;
import org.springframework.data.relational.core.sql.In;

public class SalaVO {

    //public Integer id;

    public String identificacionSala;

    public Integer asientosDisponibles;

    public Integer asientoReservados;

    public String nombreEvento;

    //public String nombrePelicula;

    public SalaVO() {
    }

    public SalaVO(Sala sala) {
      //  this.id = sala.getId();
        this.identificacionSala = sala.getIdentificacionSala();
        this.asientosDisponibles = sala.getAsientosDisponibles();
        this.asientoReservados = sala.getAsientoReservados();
        //this.idEvento = sala.getEvento().getId();
    }

    /*public SalaVO(String identifacionSala, Integer asientosDisponibles, Integer asientoReservados, Integer idEvento) {
        this.identificacionSala = identifacionSala;
        this.asientosDisponibles = asientosDisponibles;
        this.asientoReservados = asientoReservados;
        this.idEvento = idEvento;
    }*/

    public SalaVO(String identifacionSala) {
        this.identificacionSala = identifacionSala;
    }

    public SalaVO(Integer Id,String identifacionSala, Integer asientosDisponibles, Integer asientoReservados,String nombreEvento) {
       // this.id=id;
        this.identificacionSala = identifacionSala;
        this.nombreEvento=nombreEvento.toUpperCase();
        this.asientosDisponibles = asientosDisponibles;
        this.asientoReservados = asientoReservados;
    }

    public SalaVO(String identifacionSala, Integer asientosDisponibles, Integer asientoReservados) {
        this.identificacionSala = identifacionSala;
        this.asientosDisponibles = asientosDisponibles;
        this.asientoReservados = asientoReservados;
    }
}