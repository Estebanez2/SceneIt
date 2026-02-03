package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.KeywordDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "keyword")
public class Keyword implements Serializable, toDTO<KeywordDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKeyword", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToMany(mappedBy = "keywords")
    private Set<Movie> movies = new LinkedHashSet<>();

    @Override
    public KeywordDTO toDTO() {
        KeywordDTO keywordDTO = new KeywordDTO();
        keywordDTO.setId(this.id);
        keywordDTO.setName(this.name);

        return keywordDTO;
    }
}