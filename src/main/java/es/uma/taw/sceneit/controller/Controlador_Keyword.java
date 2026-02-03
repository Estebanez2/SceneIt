package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.KeywordDTO;
import es.uma.taw.sceneit.services.KeywordService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/keyword")
public class Controlador_Keyword extends Controlador_Padre{
    @Autowired
    protected KeywordService keywordService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<KeywordDTO> keyword = this.keywordService.getAllUsersAsDTO();
            model.addAttribute("keyword", keyword);

            return "keyword";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        keywordService.delete(id);

        return "redirect:/keyword/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        KeywordDTO keyword = keywordService.findById(id);
        model.addAttribute("keyword", keyword);

        return "keywordBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        KeywordDTO keyword = keywordService.findById(id);
        model.addAttribute("keywordDTO", keyword);

        return "keywordEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<KeywordDTO> keyword = keywordService.searchByName(filtro.getNombre());
        model.addAttribute("keyword", keyword);

        return "keyword";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("keywordDTO") KeywordDTO keywordDTO) {
        keywordService.save(keywordDTO);

        return "redirect:/keyword/";
    }
}
