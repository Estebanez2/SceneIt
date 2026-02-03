package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.*;
import es.uma.taw.sceneit.services.*;
import es.uma.taw.sceneit.ui.FiltroStatistics;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/statistics")
public class Controlador_Statistics extends Controlador_Padre{

    @Autowired
    protected MovieService movieService;

    @Autowired
    protected KeywordService keywordService;

    @Autowired
    protected GenderService genderService;

    @Autowired
    protected LanguageService languageService;

    @Autowired
    protected ProductionCompanyService productionCompanyService;

    @Autowired
    protected ProductionCountryService productionCountryService;

    @Autowired
    protected CrewService crewService;

    @Autowired
    protected CastService castService;

    @Autowired
    protected PersonService personService;

    @GetMapping("/")
    public String doListar(HttpSession session,  Model model, @ModelAttribute("filtroStatistics") FiltroStatistics filtroStatistics) {
        if(estaAutenticado(session, "estadista")){
            List<MovieDTO> peliculas = this.movieService.getAllUsersAsDTO();
            model.addAttribute("peliculas", peliculas);

            List<KeywordDTO> keywords = this.keywordService.getAllUsersAsDTO();
            model.addAttribute("keywords", keywords);

            List<GenderDTO> generos = this.genderService.getAllUsersAsDTO();
            model.addAttribute("generos", generos);

            List<LanguageDTO> idiomas = this.languageService.getAllUsersAsDTO();
            model.addAttribute("idiomas", idiomas);

            List<ProductionCompanyDTO> companias = this.productionCompanyService.getAllUsersAsDTO();
            model.addAttribute("companias", companias);

            List<ProductionCountryDTO> paises = this.productionCountryService.getAllUsersAsDTO();
            model.addAttribute("paises", paises);

            List<CrewDTO> crew = this.crewService.getAllUsersAsDTO();
            model.addAttribute("crew", crew);

            List<CastDTO> cast = this.castService.getAllUsersAsDTO();
            model.addAttribute("cast", cast);

            List<PersonDTO> personas = this.personService.getAllUsersAsDTO();
            model.addAttribute("personas", personas);

            List<String> posibles = new ArrayList<>(Arrays.asList("-- Selecciona una opción --", "Peliculas", "Keywords", "Generos", "Idiomas", "Companias", "Paises", "Crew", "Cast", "Personas"));
            model.addAttribute("posibles", posibles);
            return "statistics";
        } else{
            return "redirect:/";
        }
    }

    @PostMapping("/verTodosUnTipo")
    public String verTodosUnTipo(@ModelAttribute("filtroStatistics") FiltroStatistics filtroStatistics, Model model) {

        List<MovieDTO> peliculas = this.movieService.getAllUsersAsDTO();
        List<KeywordDTO> keywords = this.keywordService.getAllUsersAsDTO();
        List<GenderDTO> generos = this.genderService.getAllUsersAsDTO();
        List<LanguageDTO> idiomas = this.languageService.getAllUsersAsDTO();
        List<ProductionCountryDTO> paises = this.productionCountryService.getAllUsersAsDTO();
        List<ProductionCompanyDTO> companias = this.productionCompanyService.getAllUsersAsDTO();
        List<CrewDTO> crew = this.crewService.getAllUsersAsDTO();
        List<CastDTO> cast = this.castService.getAllUsersAsDTO();
        List<PersonDTO> personas = this.personService.getAllUsersAsDTO();
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("keywords", keywords);
        model.addAttribute("generos", generos);
        model.addAttribute("idiomas", idiomas);
        model.addAttribute("paises", paises);
        model.addAttribute("companias", companias);
        model.addAttribute("crew", crew);
        model.addAttribute("cast", cast);
        model.addAttribute("personas", personas);
        model.addAttribute("filtroStatistics", filtroStatistics);

        List<String> posibles = new ArrayList<>(Arrays.asList("-- Selecciona una opción --", "Peliculas", "Keywords", "Generos", "Idiomas", "Companias", "Paises", "Crew", "Cast", "Personas"));
        model.addAttribute("posibles", posibles);
        List<String> listaCast = new ArrayList<>(Arrays.asList("Personaje", "Actor", "Pelicula"));
        model.addAttribute("listaCast", listaCast);
        List<String> listaCrew = new ArrayList<>(Arrays.asList("Nombre", "Puesto", "Empresa"));
        model.addAttribute("listaCrew", listaCrew);
        Map<Integer, String> listaGeneros = new HashMap<>();
        listaGeneros.put(-1, "Género (Todos)");
        listaGeneros.put(0, "Hombre");
        listaGeneros.put(1, "Mujer");
        listaGeneros.put(2, "Otro");
        model.addAttribute("listaGeneros", listaGeneros);
        return "statistics";
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtroStatistics") FiltroStatistics filtroStatistics, Model model) {
        List<MovieDTO> peliculas = this.movieService.getAllUsersAsDTO();
        List<GenderDTO> generos = this.genderService.getAllUsersAsDTO();
        List<LanguageDTO> idiomas = this.languageService.getAllUsersAsDTO();
        List<ProductionCountryDTO> paises = this.productionCountryService.getAllUsersAsDTO();
        List<ProductionCompanyDTO> companias = this.productionCompanyService.getAllUsersAsDTO();
        List<CrewDTO> crew = this.crewService.getAllUsersAsDTO();
        List<CastDTO> cast = this.castService.getAllUsersAsDTO();
        List<PersonDTO> personas = this.personService.getAllUsersAsDTO();
        List<KeywordDTO> keywords = this.keywordService.getAllUsersAsDTO();

        switch (filtroStatistics.getTipoDato()){
            case "Peliculas":
                peliculas = movieService.filtrarStatistics(filtroStatistics);

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Keywords":
                keywords = keywordService.searchByName(filtroStatistics.getNombre());

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Generos":
                generos = genderService.searchByName(filtroStatistics.getNombre());

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Idiomas":
                idiomas = languageService.searchByName(filtroStatistics.getNombre());

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Paises":
                paises = productionCountryService.searchByName(filtroStatistics.getNombre());

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Companias":
                companias = productionCompanyService.searchByName(filtroStatistics.getNombre());

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Crew":
                crew = crewService.searchStatistics(filtroStatistics);
                model.addAttribute("crew", crew);

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("cast", cast);
                model.addAttribute("companias", companias);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Cast":
                cast = castService.searchStatistics(filtroStatistics);
                model.addAttribute("cast", cast);

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("companias", companias);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            case "Personas":
                personas = personService.search(filtroStatistics.getNombre(), filtroStatistics.getId(), filtroStatistics.getEsActor());
                model.addAttribute("personas", personas);

                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("companias", companias);
                model.addAttribute("cast", cast);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;
            default:
                model.addAttribute("peliculas", peliculas);
                model.addAttribute("keywords", keywords);
                model.addAttribute("generos", generos);
                model.addAttribute("idiomas", idiomas);
                model.addAttribute("paises", paises);
                model.addAttribute("companias", companias);
                model.addAttribute("crew", crew);
                model.addAttribute("cast", cast);
                model.addAttribute("personas", personas);
                model.addAttribute("filtroStatistics", filtroStatistics);
                model.addAttribute("tipoDato", filtroStatistics.getTipoDato());
                break;

        }
        List<String> posibles = new ArrayList<>(Arrays.asList("-- Selecciona una opción --", "Peliculas", "Keywords", "Generos", "Idiomas", "Companias", "Paises", "Crew", "Cast", "Personas"));
        model.addAttribute("posibles", posibles);
        List<String> listaCast = new ArrayList<>(Arrays.asList("Personaje", "Actor", "Pelicula"));
        model.addAttribute("listaCast", listaCast);
        List<String> listaCrew = new ArrayList<>(Arrays.asList("Nombre", "Puesto", "Empresa"));
        model.addAttribute("listaCrew", listaCrew);
        Map<Integer, String> listaGeneros = new HashMap<>();
        listaGeneros.put(-1, "Género (Todos)");
        listaGeneros.put(0, "Hombre");
        listaGeneros.put(1, "Mujer");
        listaGeneros.put(2, "Otro");
        model.addAttribute("listaGeneros", listaGeneros);
        return "statistics";
    }


}
