package com.application.api.vo;

import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Sala;
import com.application.api.model.evento.Seleccion;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;
import java.util.Date;

public class PartidoVO {
    public Integer id;
    public String nombreSeleccionA;
    public String nombreSeleccionB;

    public Integer calificacionEvento;
    public Float precioEvento;

    public LocalDateTime fechaEvento;
   // public String identificacionSala;

    public PartidoVO() {
    }

    public PartidoVO(Partido partido) {
        this.id = partido.getId();
        this.nombreSeleccionA=partido.getSeleccionA().getNombrePais();
        this.nombreSeleccionB=partido.getSeleccionB().getNombrePais();
        this.calificacionEvento=partido.getCalificacion();
        this.precioEvento=partido.getPrecio();
        this.fechaEvento=partido.getFechaEvento();
        //this.identificacionSala=partido.getSalaAsignada().getIdentificacionSala();
    }

    public PartidoVO(String nombreSeleccionA,String nombreSeleccionB,Integer calificacionEvento, Float precioEvento,LocalDateTime fechaEvento){
        this.nombreSeleccionA=nombreSeleccionA;
        this.nombreSeleccionB=nombreSeleccionB;
        this.calificacionEvento=calificacionEvento;
        this.precioEvento=precioEvento;
        this.fechaEvento=fechaEvento;
    }
}
