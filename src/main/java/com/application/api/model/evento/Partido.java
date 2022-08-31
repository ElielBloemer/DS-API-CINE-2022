package com.application.api.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seleccion seleccionA;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seleccion seleccionB;
    public Partido(Seleccion seleccionA,Seleccion seleccionB){
        this.seleccionA=seleccionA;
        this.seleccionB=seleccionB;
    }

}
