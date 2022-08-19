package com.application.api.persistance;

import com.application.api.model.evento.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador,Integer> {
    Jugador getJugadorById(Integer id);
}
