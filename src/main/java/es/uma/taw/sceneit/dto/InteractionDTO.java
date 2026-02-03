package es.uma.taw.sceneit.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InteractionDTO {
    private Integer id = -1;
    private Integer idUser = -1;
    private Integer idMovie = -1;
    private Integer valoration = 0;
    private Boolean recomended = true;
    private List<Integer> commentary = new ArrayList<>();
    private Boolean interactue = false;
}
