package es.uma.taw.sceneit.dao;


import es.uma.taw.sceneit.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("""
    SELECT p FROM Person p 
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nombre, '%'))
    AND (:genero NOT IN (0, 1, 2) OR p.gender = :genero)
    AND (
        :esActor IS NULL 
        OR (:esActor = TRUE AND p.actor IS NOT NULL) 
        OR (:esActor = FALSE AND p.actor IS NULL)
    )
""")
    List<Person> buscarPorFiltros(
            @Param("nombre") String nombre,
            @Param("genero") Integer genero,
            @Param("esActor") Boolean esActor
    );
}
