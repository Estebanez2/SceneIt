package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.ProductionCompanyDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "production_company")
public class ProductionCompany implements Serializable, toDTO<ProductionCompanyDTO>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduction_Company", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;


    @ManyToMany(mappedBy = "productionCompanies")
    private Set<Movie> movies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "productionCompanyIdproductionCompany")
    private Set<Crew> crews = new LinkedHashSet<>();

    public ProductionCompanyDTO toDTO() {
        ProductionCompanyDTO productionCompanyDTO = new ProductionCompanyDTO();
        productionCompanyDTO.setId(this.id);
        productionCompanyDTO.setName(this.name);

        return productionCompanyDTO;
    }
}