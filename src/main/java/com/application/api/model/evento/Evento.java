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
    /*@OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Sala salaAsignada;*/
    private String idSala;

    private boolean eventoActivo;

    public Evento(Integer calificacion,Float precio,LocalDateTime fechaEvento,String idSala,boolean eventoActivo){
        this.calificacion=calificacion;
        this.precio=precio;
        this.fechaEvento=fechaEvento;
        this.idSala=idSala;
        this.eventoActivo=eventoActivo;
    }

}
