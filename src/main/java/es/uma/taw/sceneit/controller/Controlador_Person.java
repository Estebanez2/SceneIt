package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.PersonDTO;
import es.uma.taw.sceneit.services.PersonService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class Controlador_Person extends Controlador_Padre{
    @Autowired
    protected PersonService personService;

    @GetMapping("/")
    public String doListar(HttpSession session, @ModelAttribute("filtro")Filtro filtro, Model model) {
        if(estaAutenticado(session, "editor")){
            List<PersonDTO> person = this.personService.getAllUsersAsDTO();
            model.addAttribute("person", person);

            return "person";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        this.personService.delete(id);

        return "redirect:/person/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        PersonDTO person = this.personService.findById(id);
        model.addAttribute("person", person);

        return "personBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        PersonDTO person = this.personService.findById(id);
        model.addAttribute("personDTO", person);

        return "personEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<PersonDTO> person = this.personService.search(filtro.getNombre(), filtro.getId(), filtro.getEsActor());
        model.addAttribute("person", person);

        return "person";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("personDTO") PersonDTO personDTO) {
        personService.save(personDTO);

        return "redirect:/person/";
    }
}
