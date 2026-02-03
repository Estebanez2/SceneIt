package es.uma.taw.sceneit.dto;

import lombok.Data;

@Data
public class CastDTO {
    private Integer id = -1;
    private String character;
    private Integer idActor = -1;
    private Integer idPelicula = -1;

    private String nombreActor = "";
    private String nombrePelicula = "";
}
