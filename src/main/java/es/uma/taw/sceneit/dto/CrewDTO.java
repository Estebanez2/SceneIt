package es.uma.taw.sceneit.dto;

import lombok.Data;

@Data
public class CrewDTO {
    private Integer id = -1;
    private String job;

    private Integer idPersona = -1;
    private String nombrePersona = "";

    private Integer idProductCompany= -1;
    private String nombreProductCompany = "";
}
