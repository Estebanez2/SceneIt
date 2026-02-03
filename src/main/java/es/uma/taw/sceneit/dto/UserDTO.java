package es.uma.taw.sceneit.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id = -1;
    private String username;
    private String pwd;
    private String firstName;
    private String secondName1;
    private String secondName2;
    private String email;
    private String birthDate;
    private Integer idRole = 4;
    private String nombreRole;
    private Boolean admin = false;
    private Boolean editor = false;
    private Boolean estadista = false;
    private Boolean userLectura = true;
    private Boolean userEscritura = true;
}
