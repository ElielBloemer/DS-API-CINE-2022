package com.application.api.model.evento;

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
    private Integer id;
    private String nombreJugador;
    private boolean esEstrella;

    private String nombreSeleccion;
    public Jugador(String nombre,boolean esEstrella,String nombreSeleccion){
        this.nombreJugador=nombre;
        this.esEstrella=esEstrella;
        this.nombreSeleccion=nombreSeleccion;
    }

    // @OneToOne
    // private Seleccion selecion;

}
