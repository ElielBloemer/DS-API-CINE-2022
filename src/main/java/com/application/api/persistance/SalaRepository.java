package com.application.api.persistance;

import com.application.api.model.evento.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala,Long> {
    public Sala findByIdentificacionSala(String identificacionSala);
}
