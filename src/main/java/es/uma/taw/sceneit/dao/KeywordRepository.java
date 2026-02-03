package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    @Query("select k from Keyword k where lower(k.name) like lower(concat('%', :nombre, '%'))")
    List<Keyword> buscarPorNombre (@Param("nombre") String nombre);
}
