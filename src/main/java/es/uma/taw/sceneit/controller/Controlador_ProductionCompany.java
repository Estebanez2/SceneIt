package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.ProductionCompanyDTO;
import es.uma.taw.sceneit.services.ProductionCompanyService;
import es.uma.taw.sceneit.ui.Filtro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productionCompany")
public class Controlador_ProductionCompany extends Controlador_Padre{
    @Autowired
    protected ProductionCompanyService productionCompanyService;

    @GetMapping("/")
    public String doListar(HttpSession session, Model model, @ModelAttribute("filtro") Filtro filtro) {
        if(estaAutenticado(session, "editor")){
            List<ProductionCompanyDTO> productionCompany = this.productionCompanyService.getAllUsersAsDTO();
            model.addAttribute("productionCompany", productionCompany);

            return "productionCompany";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/borrar")
    public String doBorrar(@RequestParam("id") Integer id) {
        productionCompanyService.delete(id);

        return "redirect:/productionCompany/";
    }

    @PostMapping("/menuBorrar")
    public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
        ProductionCompanyDTO productionCompany = productionCompanyService.findById(id);
        model.addAttribute("productionCompany", productionCompany);

        return "productionCompanyBorrar";
    }

    @PostMapping("/editar")
    public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
        ProductionCompanyDTO productionCompany = productionCompanyService.findById(id);
        model.addAttribute("productionCompanyDTO", productionCompany);

        return "productionCompanyEditar";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro")Filtro filtro, Model model) {
        List<ProductionCompanyDTO> productionCompany = productionCompanyService.searchByName(filtro.getNombre());
        model.addAttribute("productionCompany", productionCompany);

        return "productionCompany";
    }

    @PostMapping("/guardar")
    public String doGuardar(@ModelAttribute("productionCompanyDTO") ProductionCompanyDTO productionCompanyDTO) {
        productionCompanyService.save(productionCompanyDTO);

        return "redirect:/productionCompany/";
    }
}
