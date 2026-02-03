package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.CommentaryRepository;
import es.uma.taw.sceneit.dao.InteractionRepository;
import es.uma.taw.sceneit.dto.CommentaryDTO;

import es.uma.taw.sceneit.entity.Commentary;
import es.uma.taw.sceneit.entity.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CommentaryService extends ServiceParent<Commentary, CommentaryDTO> {
    @Autowired protected CommentaryRepository commentaryRepository;
    @Autowired protected InteractionRepository interactionRepository;
    @Autowired
    private InteractionService interactionService;

    public List<CommentaryDTO> getAllUsersAsDTO(){
        return convertToDTOList(this.commentaryRepository.findAll());
    }

    public List<CommentaryDTO> getListaPorId(List<Integer> id){
        return convertToDTOList(this.commentaryRepository.findAllById(id));
    }

    public List<CommentaryDTO> getListaPorPelicula(Integer idMovie){
        List<Commentary> lista = this.commentaryRepository.getCommentaryByMovie(idMovie);
        return convertToDTOList(lista);
    }

    public void delete(Integer id) {
        commentaryRepository.deleteById(id);
    }

    public CommentaryDTO getById(Integer id) {
        return commentaryRepository.findById(id)
                .map(Commentary::toDTO)
                .orElse(new CommentaryDTO());
    }

    public void save(CommentaryDTO dto) {
        Commentary commentary = commentaryRepository.findById(dto.getIdCom()).orElse(new Commentary());
        Interaction interaction = interactionService.getByIdEntity(dto.getIdInteractionCom(), dto.getIdMovie(), dto.getIdUserCom());
        this.interactionRepository.save(interaction);
        commentary.setInteractionIdinteraction(interaction);
        commentary.setDate(LocalDate.now());
        commentary.setContent(dto.getContent());
        this.commentaryRepository.save(commentary);
    }
}
