package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.InteractionDTO;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "interaction")
public class Interaction implements Serializable, toDTO<InteractionDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInteraction", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Movie_idMovie")
    private Movie movieIdmovie;

    @Column(name = "valoration")
    private Integer valoration;

    @Column(name = "recommended")
    private Byte recommended;

    @OneToMany(mappedBy = "interactionIdinteraction")
    private List<Commentary> commentaries = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idUser")
    private User userIduser;

    public InteractionDTO toDTO() {
        InteractionDTO interactionDTO = new InteractionDTO();
        interactionDTO.setId(getId());
        interactionDTO.setIdUser(getUserIduser().getId());
        Byte recommended = getRecommended();
        interactionDTO.setRecomended(recommended == null || recommended != 0);
        interactionDTO.setValoration(getValoration());
        List<Integer> comentariosDTO = new ArrayList<>();
        for(Commentary c: commentaries) {
            comentariosDTO.add(c.getId());
        }
        interactionDTO.setCommentary(comentariosDTO);
        interactionDTO.setIdMovie(getMovieIdmovie().getId());
        if(valoration == null){
            interactionDTO.setInteractue(false);
        } else{
            interactionDTO.setInteractue(true);
        }

        return interactionDTO;
    }

}