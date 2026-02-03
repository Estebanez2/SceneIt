package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Integer> {
    @Query("select pc from ProductionCountry pc where lower(pc.name) like lower(concat('%', :nombre, '%'))")
    List<ProductionCountry> buscarPorNombre (@Param("nombre") String nombre);
}
