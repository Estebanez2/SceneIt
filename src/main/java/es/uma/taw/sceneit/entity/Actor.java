package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.ActorDTO;
import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "actor")
public class Actor implements Serializable, toDTO<ActorDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActor", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "actorIdactor")
    private Set<Cast> casts = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "Person_idPerson", nullable = true)
    private Person personIdperson;

    public ActorDTO toDTO() {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(this.id);

        if(personIdperson != null){
            actorDTO.setEsActor(true);
            actorDTO.setName(personIdperson.getName());
        } else{
            actorDTO.setEsActor(false);
        }

        return actorDTO;
    }


}