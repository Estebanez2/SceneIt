package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
