package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.LanguageDTO;
import es.uma.taw.sceneit.services.LanguageService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/language")
public class Controlador_Language extends Controlador_Padre{
    @Autowired
    protected LanguageService languageService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<LanguageDTO> language = this.languageService.getAllUsersAsDTO();
            model.addAttribute("language", language);

            return "language";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        languageService.delete(id);

        return "redirect:/language/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        LanguageDTO language = languageService.findById(id);
        model.addAttribute("language", language);

        return "languageBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        LanguageDTO language = languageService.findById(id);
        model.addAttribute("languageDTO", language);

        return "languageEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<LanguageDTO> language = languageService.searchByName(filtro.getNombre());
        model.addAttribute("language", language);

        return "language";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("languageDTO") LanguageDTO languageDTO) {
        languageService.save(languageDTO);

        return "redirect:/language/";
    }
}
