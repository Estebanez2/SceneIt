package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.CastRepository;
import es.uma.taw.sceneit.dto.CastDTO;
import es.uma.taw.sceneit.entity.Actor;
import es.uma.taw.sceneit.entity.Cast;
import es.uma.taw.sceneit.ui.Filtro;
import es.uma.taw.sceneit.ui.FiltroStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CastService extends ServiceParent<Cast, CastDTO> {
    @Autowired
    protected CastRepository castRepository;

    @Autowired
    protected ActorService actorService;


    public List<CastDTO> getAllUsersAsDTO() {
        List<Cast> cast = castRepository.findAll();
        return convertToDTOList(cast);
    }

    public void delete(Integer id) {
        castRepository.deleteById(id);
    }

    public CastDTO findById(Integer id) {
        return castRepository.findById(id)
                .map(Cast::toDTO)
                .orElse(new CastDTO());
    }

    public List<CastDTO> searchByCharacter(String name) {
        return convertToDTOList(castRepository.buscarPorPersonaje(name));
    }

    public List<CastDTO> searchByMovie(String name) {
        return convertToDTOList(castRepository.buscarPorPelicula(name));
    }

    public List<CastDTO> searchByActor(String name) {
        return convertToDTOList(castRepository.buscarPorActor(name));
    }

    public List<CastDTO> search(Filtro filtro) {
        List<CastDTO> resultado;

        switch (filtro.getTipo()) {
            case "personaje":
                resultado = searchByCharacter(filtro.getNombre());
                break;
            case "actor":
                resultado = searchByActor(filtro.getNombre());
                break;
            case "pelicula":
                resultado = searchByMovie(filtro.getNombre());
                break;
            default:
                resultado = new ArrayList<>();
                break;
        }

        return resultado;
    }

    public List<CastDTO> searchStatistics(FiltroStatistics filtroStatistics) {
        List<CastDTO> resultado;

        switch (filtroStatistics.getTipo()) {
            case "Personaje":
                resultado = searchByCharacter(filtroStatistics.getNombre());
                break;
            case "Actor":
                resultado = searchByActor(filtroStatistics.getNombre());
                break;
            case "Pelicula":
                resultado = searchByMovie(filtroStatistics.getNombre());
                break;
            default:
                resultado = new ArrayList<>();
                break;
        }

        return resultado;
    }

    public List<CastDTO> castPorPelicula(Integer id) {
        return convertToDTOList(castRepository.buscarPorMovie(id));
    }

    public List<CastDTO> castDeMovie(Integer id) {
        return convertToDTOList(castRepository.castDeMovie(id));
    }


    public void save(CastDTO dto) {
        Cast entity = castRepository.findById(dto.getId()).orElse(new Cast());
        entity.setCharacter(dto.getCharacter());


        if(dto.getIdActor() != -1)
        {
            Actor actor = this.actorService.findEntityById(dto.getIdActor());
            entity.setActorIdactor(actor);
        } else{
            entity.setActorIdactor(null);
        }

        castRepository.save(entity);
    }

}