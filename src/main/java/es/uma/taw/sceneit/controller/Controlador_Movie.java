package es.uma.taw.sceneit.controller;

import es.uma.taw.sceneit.dto.CommentaryDTO;
import es.uma.taw.sceneit.dto.InteractionDTO;
import es.uma.taw.sceneit.dto.MovieDTO;
import es.uma.taw.sceneit.dto.UserDTO;
import es.uma.taw.sceneit.services.*;
import es.uma.taw.sceneit.ui.FiltroPeliculas;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movie") //url genérica de todo
public class Controlador_Movie extends Controlador_Padre{

    @Autowired
    private MovieService movieService;
    @Autowired
    private CastService castService;
    @Autowired
    private CrewService crewService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ProductionCountryService productionCountryService;
    @Autowired
    private ProductionCompanyService productionCompanyService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private InteractionService interactionService;
    @Autowired
    private CommentaryService commentaryService;


        @GetMapping("/")
        public String doListar(Model model, @ModelAttribute("filtro") FiltroPeliculas filtro) {
            model.addAttribute("movie", movieService.getAllUsersAsDTO());
            model.addAttribute("gender", genderService.getAllUsersAsDTO());
            model.addAttribute("language", languageService.getAllUsersAsDTO());
            return "movie";
        }

        @PostMapping("/borrar")
        public String doBorrar(@RequestParam("id") Integer id) {
            this.movieService.deleteById(id);
            return "redirect:/movie/"; //redirije de /movie/borrar a /movie/
        }

        @PostMapping("/menuBorrar")
        public String doMenuBorrar(@RequestParam("id") Integer id, Model model) {
            model.addAttribute("movie", this.movieService.getById(id));

            return "movieBorrar";
        }

        @PostMapping("/editar")
        public String doEditar(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
            model.addAttribute("cast", castService.castPorPelicula(id));
            model.addAttribute("language", languageService.getAllUsersAsDTO());
            model.addAttribute("productionCountry", productionCountryService.getAllUsersAsDTO());
            model.addAttribute("productionCompany", productionCompanyService.getAllUsersAsDTO());
            model.addAttribute("crew", crewService.getAllUsersAsDTO());
            model.addAttribute("keyword", keywordService.getAllUsersAsDTO());
            model.addAttribute("gender", genderService.getAllUsersAsDTO());
            model.addAttribute("movie", movieService.getById(id));

            return "movieEditar"; //devuelve movieEditar.jsp
        }

        @PostMapping("/guardar")
        public String doGuardar(@ModelAttribute("movie") MovieDTO movieDTO, Model model) {
            movieService.save(movieDTO);
            return "redirect:/movie/";
        }

        @GetMapping("/visualizar")
        public String doVisualizar(HttpSession session, @RequestParam(value = "id") Integer id, Model model, @ModelAttribute("interaccionUsuario") InteractionDTO interaccionUsuario){
            MovieDTO movie = this.movieService.getById(id);
            model.addAttribute("movie", movie);
            model.addAttribute("cast", castService.castDeMovie(id));
            model.addAttribute("crew", crewService.crewDeMovie(id));
            model.addAttribute("commentaries", commentaryService.getListaPorPelicula(id));

            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if(userDTO != null){
                interaccionUsuario = interactionService.getById(id, userDTO.getId());
            }

            model.addAttribute("interaccionUsuario", interaccionUsuario);
            CommentaryDTO comentario = new CommentaryDTO();
            model.addAttribute("nuevoComentario", comentario);
            return "movieVisualizar";
        }

        @PostMapping("/filtrar")
        public String doFiltrar(@ModelAttribute("filtro") FiltroPeliculas filtro, Model model) {
            List<MovieDTO> movie = this.movieService.filtrar(filtro);
            model.addAttribute("movie", movie);
            System.out.println(filtro.getLanguages());
            System.out.println(filtro.getGenre());
            model.addAttribute("gender", genderService.getAllUsersAsDTO());
            model.addAttribute("language", languageService.getAllUsersAsDTO());

            return "movie";
        }

        @PostMapping("/editarRating")
        public String doEditarRating(@ModelAttribute("interaccionUsuario")InteractionDTO interaccionUsuario) {
            this.interactionService.save(interaccionUsuario);
            return "redirect:/movie/visualizar?id=" + interaccionUsuario.getIdMovie();
        }

        @PostMapping("/editarComentario")
        public String doEditarComentario(@ModelAttribute("nuevoComentario") CommentaryDTO comentario) {
            this.commentaryService.save(comentario);
            return "redirect:/movie/visualizar?id=" + comentario.getIdMovie();
        }

        @PostMapping("/borrarComentario")
        public String doBorrarComentario(@RequestParam("idBorrar") Integer idBorrar, @RequestParam("idPelicula") Integer idPelicula){
            this.commentaryService.delete(idBorrar);
            return "redirect:/movie/visualizar?id=" + idPelicula;
        }
}
