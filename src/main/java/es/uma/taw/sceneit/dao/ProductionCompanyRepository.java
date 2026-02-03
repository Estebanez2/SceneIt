package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
    @Query("select pc from ProductionCompany pc where lower(pc.name) like lower(concat('%', :nombre, '%'))")
    List<ProductionCompany> buscarPorNombre (@Param("nombre") String nombre);
}
