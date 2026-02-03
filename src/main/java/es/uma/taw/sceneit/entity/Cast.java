package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.CastDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "cast")
public class Cast implements Serializable, toDTO<CastDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCast", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Movie_idMovie", nullable = true)
    private Movie movieIdmovie;

    @Column(name = "`character`", length = 60)
    private String character;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Actor_idActor", nullable = true)
    private Actor actorIdactor;

    public CastDTO toDTO() {
        CastDTO castDTO = new CastDTO();
        castDTO.setId(this.id);
        castDTO.setCharacter(this.character);
        if(actorIdactor != null){
            castDTO.setIdActor(actorIdactor.getId());
            castDTO.setNombreActor(actorIdactor.getPersonIdperson().getName());
        }
        if(movieIdmovie != null){
            castDTO.setIdPelicula(movieIdmovie.getId());
            castDTO.setNombrePelicula(movieIdmovie.getTitle());
        }

        return castDTO;
    }

}