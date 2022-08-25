package com.application.api.persistance;

import com.application.api.model.evento.Seleccion;
import com.application.api.vo.SeleccionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeleccionRepository extends JpaRepository<Seleccion,Long> {

   public Seleccion findByNombrePais(String nombrePais);

   //public List<Seleccion> findByContinente(String continente);
}
