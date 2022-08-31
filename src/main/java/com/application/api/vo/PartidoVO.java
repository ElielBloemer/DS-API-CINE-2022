package com.application.api.vo;

import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Sala;
import com.application.api.model.evento.Seleccion;
import org.springframework.data.relational.core.sql.In;

public class PartidoVO {
    public Integer id;
    public String seleccionA;
    public String seleccionB;

    public Integer calificacionEvento;
    public Float precioEvento;
    public String identificacionSala;

    public PartidoVO() {
    }

    public PartidoVO(Partido partido) {
        this.id = partido.getId();
        this.seleccionA=partido.getSeleccionA().getNombrePais();
        this.seleccionB=partido.getSeleccionB().getNombrePais();
        this.calificacionEvento=partido.getCalificacion();
        this.precioEvento=partido.getPrecio();
        this.identificacionSala=partido.getSalaAsignada().getIdentificacionSala();
    }

   /* public PartidoVO(String nombreSeleccionA,String nombreSeleccionB){
        this.seleccionA=seleccionA;
        this.seleccionB=seleccionB;
    }
*/
}
