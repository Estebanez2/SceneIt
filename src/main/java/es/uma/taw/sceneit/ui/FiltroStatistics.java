package es.uma.taw.sceneit.ui;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FiltroStatistics {
    private String tipoDato;
    private String nombre = "";

    private String tipo;
    private Integer id = -1;
    private Boolean esActor;

    protected Integer genre = -1;
    protected List<Integer> languages = new ArrayList<>();
}
