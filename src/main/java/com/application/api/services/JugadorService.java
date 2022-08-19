package com.application.api.services;

import com.application.api.model.evento.Actor;
import com.application.api.model.evento.Jugador;
import com.application.api.persistance.JugadorRepository;
import com.application.api.services.interfaces.IJugadorService;
import com.application.api.vo.JugadorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class JugadorService implements IJugadorService {

    private JugadorRepository jugadorRepository;

    @Autowired
    public void setJugadorRepository(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public Jugador getJugadorById(Integer id) {
        return jugadorRepository.findById(id).orElseThrow(()-> new NotFoundException(" El jugador com el id: "+id+"no existe en nuestro registros"));
    }

    @Override
    public Jugador guardarJugador(JugadorVO jugadorVO) {
        return guardarJugador(jugadorVO.nombre);
    }

    @Override
    public Jugador guardarJugador(String nombre) {
        String nombreMayusculo = nombre.toUpperCase();
        return jugadorRepository.save(new Jugador(nombreMayusculo));
    }
}
