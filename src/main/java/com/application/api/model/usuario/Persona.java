package com.application.api.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreYApellido;
    private Date dateOfBirth;
    private Integer dni;

    public Persona(String nombreYApellido, Date dateOfBirth, Integer dni) {
        this.nombreYApellido = nombreYApellido;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
    }
}
