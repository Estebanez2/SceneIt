package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.ProductionCountryDTO;
import es.uma.taw.sceneit.services.ProductionCountryService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productionCountry")
public class Controlador_ProductionCountry extends Controlador_Padre{
    @Autowired
    protected ProductionCountryService productionCountryService;

    @GetMapping("/")
    public String doListar(HttpSession session,  Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<ProductionCountryDTO> productionCountry = this.productionCountryService.getAllUsersAsDTO();
            model.addAttribute("productionCountry", productionCountry);

            return "productionCountry";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        productionCountryService.delete(id);

        return "redirect:/productionCountry/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        ProductionCountryDTO productionCountry = productionCountryService.findById(id);
        model.addAttribute("productionCountry", productionCountry);

        return "productionCountryBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        ProductionCountryDTO productionCountry = productionCountryService.findById(id);
        model.addAttribute("productionCountryDTO", productionCountry);

        return "productionCountryEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<ProductionCountryDTO> productionCountry = productionCountryService.searchByName(filtro.getNombre());
        model.addAttribute("productionCountry", productionCountry);

        return "productionCountry";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("productionCountryDTO") ProductionCountryDTO productionCountryDTO) {
        productionCountryService.save(productionCountryDTO);

        return "redirect:/productionCountry/";
    }
}