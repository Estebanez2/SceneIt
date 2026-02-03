package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.LanguageDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "language")
public class Language implements Serializable, toDTO<LanguageDTO>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLanguage", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "languages")
    private Set<Movie> movies = new LinkedHashSet<>();

    @Override
    public LanguageDTO toDTO() {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(this.id);
        languageDTO.setName(this.name);

        return languageDTO;
    }

}