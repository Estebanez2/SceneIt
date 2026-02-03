package es.uma.taw.sceneit.ui;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FiltroPeliculas {
    protected String nombre = "";
    protected Integer genre = -1;
    protected List<Integer> languages = new ArrayList<>();
}
