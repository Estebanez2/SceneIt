package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.CrewDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "crew")
public class Crew implements Serializable,toDTO<CrewDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCrew", nullable = false)
    private Integer id;

    @Column(name = "job", length = 45)
    private String job;

    @ManyToMany(mappedBy = "crews")
    private Set<Movie> movies = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_idPerson")
    private Person personIdperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Production_Company_idProduction_Company", nullable = true)

    private ProductionCompany productionCompanyIdproductionCompany;

    public CrewDTO toDTO() {
        CrewDTO crewDTO = new CrewDTO();
        crewDTO.setId(id);
        crewDTO.setJob(job);
        crewDTO.setIdPersona(personIdperson.getId());
        crewDTO.setNombrePersona(getPersonIdperson().getName());

        if(productionCompanyIdproductionCompany != null){
            crewDTO.setIdProductCompany(productionCompanyIdproductionCompany.getId());
            crewDTO.setNombreProductCompany(productionCompanyIdproductionCompany.getName());
        }

        return crewDTO;
    }
}