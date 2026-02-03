package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class Controlador_Padre {

    protected boolean estaAutenticado(HttpSession session, String permiso) {
        Boolean autentificado = false;

        if(session.getAttribute("user") != null){
            UserDTO usuario = (UserDTO) session.getAttribute("user");
            switch (permiso) {
                case "admin":
                    if(usuario.getAdmin()) {autentificado = true;}
                    break;
                case "editor":
                    if(usuario.getEditor()) {autentificado = true;}
                    break;
                case "estadista":
                    if(usuario.getEstadista()) {autentificado = true;}
                    break;
                default:
                    break;
            }
        }

        return autentificado;
    }

}
