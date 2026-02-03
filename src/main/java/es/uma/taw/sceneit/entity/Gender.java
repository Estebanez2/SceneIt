package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.GenderDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "gender")
public class Gender implements Serializable, toDTO<GenderDTO> {
    @Id
    @Column(name = "idGender", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "genders")
    private Set<Movie> movies = new LinkedHashSet<>();

    public GenderDTO toDTO() {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(this.id);
        genderDTO.setName(this.name);

        return genderDTO;
    }

}