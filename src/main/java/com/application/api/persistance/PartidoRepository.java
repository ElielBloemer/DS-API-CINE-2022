package com.application.api.persistance;

import com.application.api.model.evento.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido,Integer> {

    public Partido findPartidoById(Integer idPartido);

    public List<Partido> findAll();
}
