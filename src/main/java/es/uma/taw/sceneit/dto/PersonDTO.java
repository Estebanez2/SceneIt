package es.uma.taw.sceneit.dto;

import lombok.Data;

@Data
public class PersonDTO {
    private Integer id = -1;
    private String name;
    private Integer gender;
    private Boolean actor;

    public String getGenderString(int gender) {
        switch (gender){
            case 0: return "Hombre";
            case 1: return "Mujer";
            case 2: return "Otro";
            default: return "";
        }
    }
}
