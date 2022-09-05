package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Partido extends Evento{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;*/

    @ManyToOne(fetch = FetchType.LAZY)
    private Seleccion seleccionA;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seleccion seleccionB;
    public Partido(Seleccion seleccionA, Seleccion seleccionB, Integer calificacion, Float precio, LocalDateTime fechaEvento,String idSala,boolean estaActiva){
        //super(calificacion,precio,sala);
        super(calificacion,precio,fechaEvento,idSala,estaActiva);
        this.seleccionA=seleccionA;
        this.seleccionB=seleccionB;
    }

    /*public Partido(Seleccion seleccionA, Seleccion seleccionB){
        super(calificacion,precio,sala);
        this.seleccionA=seleccionA;
        this.seleccionB=seleccionB;
    }*/

}
