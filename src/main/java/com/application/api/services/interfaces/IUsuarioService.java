package com.application.api.services.interfaces;

import com.application.api.model.usuario.Usuario;

import java.text.ParseException;

public interface IUsuarioService {

    public boolean esMayorDeEdad(Usuario usuario) throws ParseException;
}
