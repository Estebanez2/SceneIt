package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.CommentaryRepository;
import es.uma.taw.sceneit.dao.InteractionRepository;
import es.uma.taw.sceneit.dao.MovieRepository;
import es.uma.taw.sceneit.dao.UserRepository;
import es.uma.taw.sceneit.dto.InteractionDTO;
import es.uma.taw.sceneit.entity.Commentary;
import es.uma.taw.sceneit.entity.Interaction;
import es.uma.taw.sceneit.entity.Movie;
import es.uma.taw.sceneit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InteractionService extends ServiceParent<Interaction, InteractionDTO>{
    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    public InteractionDTO getById(Integer idMovie, Integer idUser) {
        Interaction interaction = interactionRepository.buscarPorMovieYUsuario(idMovie, idUser);
        InteractionDTO interactionDTO = new InteractionDTO();
        if(interaction != null){
           interactionDTO = interaction.toDTO();
        }

        return interactionDTO;
    }

    public Interaction getByIdEntity(Integer id, Integer idMovie, Integer idUser) {
        Interaction interaction =  interactionRepository.findById(id).orElse(new Interaction());
        if (interaction.getMovieIdmovie()==null) {
            interaction.setMovieIdmovie(movieRepository.findById(idMovie).orElse(new Movie()));
        }
        if (interaction.getUserIduser()==null) {
            interaction.setUserIduser(userRepository.findById(idUser).orElse(new User()));
        }
        return interaction;
    }

    public void save(InteractionDTO interactionDTO) {
        Interaction entity = interactionRepository.findById(interactionDTO.getId()).orElse(new Interaction());
        entity.setValoration(interactionDTO.getValoration());
        if (interactionDTO.getRecomended()) {
            entity.setRecommended((byte) 1);
        }else{
            entity.setRecommended((byte) 0);
        }
        List<Commentary> lista = new ArrayList<>();
        for(Integer i : interactionDTO.getCommentary()) lista.add(commentaryRepository.getById(i));
        entity.setCommentaries(lista);
        Movie movie = movieRepository.findById(interactionDTO.getIdMovie()).orElse(new Movie());
        entity.setMovieIdmovie(movie);
        User user = userRepository.findById(interactionDTO.getIdUser()).orElse(new User());
        entity.setUserIduser(user);
        interactionRepository.save(entity);
    }

    public List<InteractionDTO> getAllUsersAsDTO() {
        List<Interaction> interactionDTOS = interactionRepository.findAll();
        return convertToDTOList(interactionDTOS);
    }

    public List<InteractionDTO> getListaPorId(List<Integer> lista) {
        List<Interaction> interactionDTOS = interactionRepository.findAllById(lista);
        return convertToDTOList(interactionDTOS);
    }

}
