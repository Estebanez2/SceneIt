package es.uma.taw.sceneit.dto;

import lombok.Data;

@Data
public class CommentaryDTO {
    private Integer idCom= -1;
    private String date = "";
    private String content = "";
    private Integer idInteractionCom = -1;
    private Integer idMovie = -1;
    private Integer idUserCom = -1;
    private String nameUserInteraction;
}
