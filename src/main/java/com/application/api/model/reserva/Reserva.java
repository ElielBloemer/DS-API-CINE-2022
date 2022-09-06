package com.application.api.model.reserva;

import com.application.api.model.combo.Combo;
import com.application.api.model.evento.Evento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idReserva;
    private Integer cantidadEntradas;
    @OneToOne
    private Evento evento;
    // TODO - relacionar el COMBO con reserva y USUARIO
    //@OneToMany
   // private Combo combo;
    private Float descuentoOtorgado;
    private String TipoDeDescuento;
    private boolean reservaActiva;

    private float costoTotal;

    public Reserva(String idReserva, Integer cantidadEntradas, Evento evento, Combo combo, Float descuentoOtorgado, String TipoDeDescuento, boolean reservaActiva,float costoTotal) {
        this.idReserva = idReserva;
        this.cantidadEntradas = cantidadEntradas;
        this.evento = evento;
        //this.combo = combo;
        this.descuentoOtorgado = descuentoOtorgado;
        this.TipoDeDescuento = TipoDeDescuento;
        this.reservaActiva = reservaActiva;
        this.costoTotal=costoTotal;
    }

    public Reserva() {
    }
}
