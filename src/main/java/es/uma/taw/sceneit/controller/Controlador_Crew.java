package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.CrewDTO;
import es.uma.taw.sceneit.dto.PersonDTO;
import es.uma.taw.sceneit.dto.ProductionCompanyDTO;
import es.uma.taw.sceneit.services.CrewService;
import es.uma.taw.sceneit.services.PersonService;
import es.uma.taw.sceneit.services.ProductionCompanyService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crew")
public class Controlador_Crew extends Controlador_Padre{
    @Autowired
    protected CrewService crewService;
    @Autowired
    protected PersonService personService;
    @Autowired
    protected ProductionCompanyService productionCompanyService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro")Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<CrewDTO> crew = crewService.getAllUsersAsDTO();
            model.addAttribute("crew", crew);

            return "crew";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        this.crewService.delete(id);

        return "redirect:/crew/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        CrewDTO crew = crewService.findById(id);
        model.addAttribute("crew", crew);

        return "crewBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        CrewDTO crew = crewService.findById(id);
        List<PersonDTO> person = personService.getAllUsersAsDTO();
        List<ProductionCompanyDTO> productionCompany = productionCompanyService.getAllUsersAsDTO();

        model.addAttribute("crewDTO", crew);
        model.addAttribute("person", person);
        model.addAttribute("productionCompany", productionCompany);

        return "crewEditar";
    }

    @PostMapping("/filtrar")
    public String filtrarCast(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<CrewDTO> resultado = crewService.search(filtro);
        model.addAttribute("crew", resultado);

        return "crew";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("crewDTO") CrewDTO crewDTO){
        crewService.save(crewDTO);

        return "redirect:/crew/";
    }
}
