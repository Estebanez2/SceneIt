package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(""" 
    SELECT m FROM Movie m
    LEFT JOIN m.languages l
    LEFT JOIN m.genders g
    WHERE lower(m.title) LIKE lower(concat ('%', :nombre, '%'))
    AND (((:genero = -1) or (:genero = g.id)))
    AND (((:size = 0) or (-1 in :idiomas) or ( l.id IN (:idiomas))))

""")
    List<Movie> buscarPorFiltros(@Param("nombre") String nombre, @Param("genero") Integer genero, @Param("idiomas")List<Integer> idiomas, @Param("size") Integer size);

}
