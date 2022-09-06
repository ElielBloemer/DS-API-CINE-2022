package com.application.api.persistance;

import com.application.api.model.reserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    public Reserva findByIdReserva(String idReserva);
}
