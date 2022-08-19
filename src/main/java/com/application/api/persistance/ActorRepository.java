package com.application.api.persistance;

import com.application.api.model.evento.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends JpaRepository<Actor,Integer> {

       Actor getActorById(Integer id);

}
