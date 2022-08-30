package com.application.api.persistance;

import com.application.api.model.evento.Pelicula;
import com.application.api.model.evento.Seleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {
    public Pelicula findByNombrePelicula(String nombrePelicula);
}
