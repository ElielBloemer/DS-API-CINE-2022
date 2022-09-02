package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer calificacion;
    private Float precio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",shape = JsonFormat.Shape.STRING)
    private LocalDateTime fechaEvento;
   // @OneToOne(fetch = FetchType.LAZY)
   // @JsonIgnore
   // private Sala salaAsignada;

    public Evento(Integer calificacion,Float precio,LocalDateTime fechaEvento){
        this.calificacion=calificacion;
        this.precio=precio;
        this.fechaEvento=fechaEvento;
        //this.salaAsignada=sala;
    }

}
