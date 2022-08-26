package com.application.api.persistance;

import com.application.api.model.evento.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {
       public Actor findByNombreActor(String nombreActor);
}
