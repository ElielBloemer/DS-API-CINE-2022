package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer calificacion;

    private Float precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Sala salaAsignada;

    public Evento(Integer calificacion,Float precio,Sala sala){
        this.calificacion=calificacion;
        this.precio=precio;
        this.salaAsignada=sala;
    }

}
