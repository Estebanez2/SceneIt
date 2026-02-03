package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.ActorDTO;
import es.uma.taw.sceneit.dto.CastDTO;
import es.uma.taw.sceneit.services.ActorService;
import es.uma.taw.sceneit.services.CastService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cast")
public class Controlador_Cast extends Controlador_Padre{
    @Autowired
    protected CastService castService;
    @Autowired
    protected ActorService actorService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<CastDTO> cast = this.castService.getAllUsersAsDTO();
            model.addAttribute("cast", cast);

            return "cast";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        this.castService.delete(id);

        return "redirect:/cast/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        CastDTO cast = castService.findById(id);
        model.addAttribute("cast", cast);

        return "castBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        CastDTO cast = castService.findById(id);
        List<ActorDTO> actor = actorService.getAllUsersAsDTO();

        model.addAttribute("castDTO", cast);
        model.addAttribute("actor", actor);

        return "castEditar";
    }

    @PostMapping("/filtrar")
    public String filtrarCast(@ModelAttribute("filtro") Filtro filtro, Model model) {
        List<CastDTO> resultado = castService.search(filtro);
        model.addAttribute("cast", resultado);

        return "cast";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("castDTO") CastDTO castDTO){
        castService.save(castDTO);

        return "redirect:/cast/";
    }
}
