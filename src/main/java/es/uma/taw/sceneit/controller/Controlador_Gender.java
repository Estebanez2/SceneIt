package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.GenderDTO;
import es.uma.taw.sceneit.services.GenderService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gender")
public class Controlador_Gender extends Controlador_Padre{
    @Autowired
    protected GenderService genderService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<GenderDTO> gender = this.genderService.getAllUsersAsDTO();
            model.addAttribute("gender", gender);

            return "gender";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        genderService.delete(id);

        return "redirect:/gender/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        GenderDTO gender = genderService.findById(id);
        model.addAttribute("gender", gender);

        return "genderBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        GenderDTO gender = genderService.findById(id);
        model.addAttribute("genderDTO", gender);

        return "genderEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<GenderDTO> gender = genderService.searchByName(filtro.getNombre());
        model.addAttribute("gender", gender);

        return "gender";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("genderDTO") GenderDTO genderDTO) {
        genderService.save(genderDTO);

        return "redirect:/gender/";
    }
}
