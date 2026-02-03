package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CastRepository extends JpaRepository<Cast, Integer> {
    @Query("SELECT c FROM Cast c WHERE LOWER(c.character) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Cast> buscarPorPersonaje(@Param("texto") String texto);

    @Query("SELECT c FROM Cast c " +
            "WHERE c.actorIdactor IS NOT NULL " +
            "AND c.actorIdactor.personIdperson IS NOT NULL " +
            "AND LOWER(c.actorIdactor.personIdperson.name) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Cast> buscarPorActor(@Param("texto") String texto);

    @Query("SELECT c FROM Cast c " +
            "WHERE c.movieIdmovie IS NOT NULL " +
            "AND LOWER(c.movieIdmovie.title) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Cast> buscarPorPelicula(@Param("texto") String texto);

    @Query("SELECT c FROM Cast c WHERE c.movieIdmovie IS NULL OR c.movieIdmovie.id = :movie")
    List<Cast> buscarPorMovie(@Param("movie") Integer movie);

    @Query("SELECT c FROM Cast c WHERE c.movieIdmovie.id = :movie")
    List<Cast> castDeMovie(@Param("movie") Integer movie);

}
