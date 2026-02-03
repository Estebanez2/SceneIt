package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
    @Query("SELECT c FROM Crew c WHERE LOWER(c.job) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Crew> buscarPorPuesto(@Param("texto") String texto);

    @Query("SELECT c FROM Crew c " +
            "WHERE c.personIdperson IS NOT NULL " +
            "AND LOWER(c.personIdperson.name) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Crew> buscarPorNombre(@Param("texto") String texto);

    @Query("SELECT c FROM Crew c " +
            "WHERE c.productionCompanyIdproductionCompany IS NOT NULL " +
            "AND LOWER(c.productionCompanyIdproductionCompany.name) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Crew> buscarPorEmpresa(@Param("texto") String texto);


    @Query("SELECT c FROM Crew c LEFT JOIN c.movies m WHERE m.id = :movie")
    List<Crew> crewDeMovie(@Param("movie") Integer movie);
}
