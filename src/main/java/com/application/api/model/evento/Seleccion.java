package com.application.api.model.evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Seleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombrePais;
    private String continente;
    @OneToMany
    private List<Jugador> jugadorTitulares;
    private Integer mundialesGanados;

    public Seleccion(String nombrePais,String continente, List<Jugador> jugadores, Integer mundialesGanados){
        this.nombrePais = nombrePais;
        this.continente = continente;
        this.jugadorTitulares = jugadores;
        this.mundialesGanados = mundialesGanados;
    }

}
