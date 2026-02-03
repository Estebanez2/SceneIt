package es.uma.taw.sceneit.ui;

import lombok.Data;

@Data
public class Filtro {
    private String nombre;
    private String tipo;
    private Integer id = -1;
    private Boolean esActor;
}
