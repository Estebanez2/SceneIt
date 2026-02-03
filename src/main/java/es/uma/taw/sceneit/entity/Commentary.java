package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.CommentaryDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "commentary")
public class Commentary implements Serializable, toDTO<CommentaryDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCommentary", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Interaction_idInteraction")
    private Interaction interactionIdinteraction;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "content", length = 200)
    private String content;

    @Override
    public CommentaryDTO toDTO(){
        CommentaryDTO comDTO = new CommentaryDTO();
        comDTO.setContent(content);
        comDTO.setIdCom(id);
        comDTO.setIdInteractionCom(interactionIdinteraction.getId());
        comDTO.setDate((String.format("%04d-%02d-%02d",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth())));
        comDTO.setNameUserInteraction(interactionIdinteraction.getUserIduser().getUsername());
        comDTO.setIdMovie(getInteractionIdinteraction().getMovieIdmovie().getId());
        comDTO.setIdUserCom(getInteractionIdinteraction().getUserIduser().getId());

        return comDTO;

    }

}