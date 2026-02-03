package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query("select l from Language l where lower(l.name) like lower(concat('%', :nombre, '%'))")
    List<Language> buscarPorNombre (@Param("nombre") String nombre);
}
