package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    @Query("select g from Gender g where lower(g.name) like lower(concat('%', :nombre, '%'))")
    List<Gender> buscarPorNombre (@Param("nombre") String nombre);
}
