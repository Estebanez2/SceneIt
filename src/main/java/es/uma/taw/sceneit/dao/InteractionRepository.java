package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InteractionRepository extends JpaRepository<Interaction, Integer> {
    @Query("select i from Interaction i where i.movieIdmovie.id = :idMovie AND i.userIduser.id = :idUsuario")
    Interaction buscarPorMovieYUsuario (@Param("idMovie") Integer idMovie, @Param("idUsuario") Integer idUsuario);
}
