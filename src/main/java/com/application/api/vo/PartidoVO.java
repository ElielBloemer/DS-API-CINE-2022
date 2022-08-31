package com.application.api.vo;

import com.application.api.model.evento.Partido;
import com.application.api.model.evento.Seleccion;

public class PartidoVO {
    public Integer id;
    public String seleccionA;
    public String seleccionB;

    public PartidoVO() {
    }

    public PartidoVO(Partido partido) {
        this.id = partido.getId();
        this.seleccionA=partido.getSeleccionA().getNombrePais();
        this.seleccionB=partido.getSeleccionB().getNombrePais();
    }

   /* public PartidoVO(String nombreSeleccionA,String nombreSeleccionB){
        this.seleccionA=seleccionA;
        this.seleccionB=seleccionB;
    }
*/
}
