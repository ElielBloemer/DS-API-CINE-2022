package com.application.api.model.usuario;

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
    //metodoDePago
    @OneToOne
    private Persona persona;

    public Usuario(String nombreUsuario, Persona persona) {
        this.nombreUsuario = nombreUsuario;
        this.persona = persona;
    }
}
