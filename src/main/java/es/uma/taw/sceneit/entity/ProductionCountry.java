package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.ProductionCountryDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "production_country")
public class ProductionCountry implements Serializable, toDTO<ProductionCountryDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduction_Country", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "productionCountries")
    private Set<Movie> movies = new LinkedHashSet<>();

    @Override
    public ProductionCountryDTO toDTO() {
        ProductionCountryDTO productionCountryDTO = new ProductionCountryDTO();
        productionCountryDTO.setId(this.id);
        productionCountryDTO.setName(this.name);

        return productionCountryDTO;
    }

}