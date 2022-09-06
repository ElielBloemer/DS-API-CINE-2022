package com.application.api.services.usuario;

import com.application.api.model.usuario.Usuario;
import com.application.api.services.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class UsuarioService implements IUsuarioService {
    @Override
    public boolean esMayorDeEdad(Usuario usuario) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(usuario.getPersona().getDateOfBirth().toString());
        Date fechaDeHoy=new Date();
        Date secondDate = sdf.parse(fechaDeHoy.toString());
        long diff=secondDate.getTime()-firstDate.getTime();
        return diff <= 18;
    }
}
