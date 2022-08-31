package com.application.api.model.evento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identificacionSala;

    private Integer asientosDisponibles;

    private Integer asientoReservados;

    private boolean tieneEventoAsignado;

    public Sala(String identifacionSala, Integer asientosDisponibles, Integer asientoReservados,boolean tieneEventoAsignado) {
        this.identificacionSala = identifacionSala;
        this.asientosDisponibles = asientosDisponibles;
        this.asientoReservados = asientoReservados;
        this.tieneEventoAsignado=tieneEventoAsignado;
    }
}
