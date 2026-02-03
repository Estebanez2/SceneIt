package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.ActorRepository;
import es.uma.taw.sceneit.dto.ActorDTO;
import es.uma.taw.sceneit.entity.Actor;
import es.uma.taw.sceneit.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService extends ServiceParent<Actor, ActorDTO>{
    @Autowired
    protected ActorRepository actorRepository;

    public List<ActorDTO> getAllUsersAsDTO() {
        List<Actor> actor = actorRepository.findAll();
        return convertToDTOList(actor);
    }

    public void delete(Integer id) {
        actorRepository.deleteById(id);
    }

    public ActorDTO findById(Integer id) {
        return actorRepository.findById(id)
                .map(Actor::toDTO)
                .orElse(new ActorDTO());
    }

    public Actor findEntityById(Integer id) {
        return actorRepository.findById(id).orElse(null);
    }

    public void save(ActorDTO dto, Person person) {
        Actor entity = actorRepository.findById(dto.getId()).orElse(new Actor());
        entity.setPersonIdperson(person);

        actorRepository.save(entity);
    }

}
