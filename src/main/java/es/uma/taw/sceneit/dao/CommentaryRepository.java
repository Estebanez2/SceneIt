package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
    @Query("Select c FROM Commentary c left join c.interactionIdinteraction i WHERE i.movieIdmovie.id = :movie ORDER BY c.date")
    List<Commentary> getCommentaryByMovie(@Param("movie") Integer movie);
}
