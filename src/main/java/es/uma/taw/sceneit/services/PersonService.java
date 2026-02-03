package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.PersonRepository;
import es.uma.taw.sceneit.dto.ActorDTO;
import es.uma.taw.sceneit.dto.PersonDTO;
import es.uma.taw.sceneit.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends ServiceParent<Person, PersonDTO>{
    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected ActorService actorService;

    public List<PersonDTO> getAllUsersAsDTO() {
        List<Person> person = personRepository.findAll();
        return convertToDTOList(person);
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

    public PersonDTO findById(Integer id) {
        return personRepository.findById(id)
                .map(Person::toDTO)
                .orElse(new PersonDTO());
    }

    public Person findEntityById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<PersonDTO> search(String name, Integer id, Boolean esActor) {
        return convertToDTOList(personRepository.buscarPorFiltros(name, id, esActor));
    }

    public void save(PersonDTO dto) {
        Person entity = personRepository.findById(dto.getId()).orElse(new Person());
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());

        boolean quiereActor = dto.getActor();
        boolean teniaActor = entity.getActor() != null;

        int idActorAntiguo = -1;
        if(teniaActor){
            idActorAntiguo = entity.getActor().getId();
        }

        if (quiereActor) {
            this.personRepository.save(entity);
            ActorDTO actorDTO = actorService.findById(teniaActor ? idActorAntiguo : -1);
            actorService.save(actorDTO, entity);
        } else {
            if (teniaActor) {
                entity.setActor(null);
                this.personRepository.save(entity);
                actorService.delete(idActorAntiguo);
            } else{
                this.personRepository.save(entity);
            }
        }

    }

}
