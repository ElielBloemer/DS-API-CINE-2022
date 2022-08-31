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
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String nombreJugador;
    private boolean esEstrella;
   // private String nombreSeleccion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Seleccion seleccion;
    public Jugador(String nombre,boolean esEstrella,Seleccion seleccion){
        this.nombreJugador=nombre;
        this.esEstrella=esEstrella;
        this.seleccion=seleccion;
    }

}
