package com.application.api.persistance;

import com.application.api.model.evento.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JugadorRepository extends JpaRepository<Jugador,Long> {
    public Jugador getJugadorById(Integer id);
    public Jugador findByNombreJugador(String nombreJugador);

}
