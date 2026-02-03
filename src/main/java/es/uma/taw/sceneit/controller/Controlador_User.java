package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.RoleDTO;
import es.uma.taw.sceneit.dto.UserDTO;
import es.uma.taw.sceneit.services.RoleService;
import es.uma.taw.sceneit.services.UserService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class Controlador_User extends Controlador_Padre{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro")Filtro filtro) {
        if(estaAutenticado(session, "admin")){
            List<UserDTO> user = this.userService.getAllUsersAsDTO();
            model.addAttribute("user", user);

            return "user";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(HttpSession session, @RequestParam("id") Integer id) {
        this.userService.delete(id);
        if(userService.comprobarUsuarioIgual((UserDTO) session.getAttribute("user"), id)){
            return "redirect:/user/logout";
        } else{
            return "redirect:/user/";
        }
    }


    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        UserDTO user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "userBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        UserDTO user = this.userService.findById(id);
        List<RoleDTO> role = this.roleService.getAllUsersAsDTO();

        model.addAttribute("role", role);
        model.addAttribute("userDTO", user);

        return "userEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(Model model, @ModelAttribute("filtro")Filtro filtro) {
        List<UserDTO> user = this.userService.searchByName(filtro.getNombre());
        model.addAttribute("user", user);

        return "user";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("userDTO") UserDTO userDTO){
        userService.save(userDTO);

        return "redirect:/user/";
    }

    @PostMapping("/guardarUser")
    public String doGuardarUser(@ModelAttribute("userDTO") UserDTO userDTO, HttpSession session){
        Integer id = userService.save(userDTO);
        userDTO.setId(id);
        session.setAttribute("user", userDTO);

        return "redirect:/movie/";
    }

    @GetMapping("/login")
    public String doLogin(@ModelAttribute("userDTO") UserDTO userDTO){

        return "login";
    }

    @PostMapping("/logear")
    public String doLogear(@ModelAttribute("userDTO") UserDTO userDTO, Model model, HttpSession session){
        UserDTO usuarioDTO = this.userService.loguear(userDTO.getUsername(), userDTO.getPwd());

        if (usuarioDTO == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectas");
            return "login";
        } else {
            session.setAttribute("user", usuarioDTO);
            return "redirect:/movie/";
        }
    }

    @GetMapping("/logout")
    public String doLogout (HttpSession session) {
        session.invalidate();
        return "redirect:/movie/";
    }


    @GetMapping("/editarUser")
    public String doLogout (Model model, @ModelAttribute("userDTO") UserDTO userDTO, @RequestParam(value = "id") Integer id) {
;       userDTO = this.userService.findById(id);
        model.addAttribute("userDTO", userDTO);

        return "userEditarUser";
    }
}
