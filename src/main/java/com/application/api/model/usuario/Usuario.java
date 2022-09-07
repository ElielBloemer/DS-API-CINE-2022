package com.application.api.model.usuario;

import com.application.api.model.tarjetadedebito.TarjetaDeDebito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreUsuario;
    //TODO
    //cuenta asociada
    //avatar
    //reserva
    @OneToOne
    private TarjetaDeDebito tarjetaDeDebito;
    @OneToOne
    private Persona persona;

    public Usuario(String nombreUsuario, Persona persona,TarjetaDeDebito tarjetaDeDebito) {
        this.nombreUsuario = nombreUsuario;
        this.persona = persona;
        this.tarjetaDeDebito=tarjetaDeDebito;
    }
}
