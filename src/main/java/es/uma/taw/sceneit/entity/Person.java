package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "person")
public class Person implements Serializable, toDTO<PersonDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "gender")
    private Integer gender;


    @OneToMany(mappedBy = "personIdperson")
    private Set<Crew> crews = new LinkedHashSet<>();

    @OneToOne(mappedBy = "personIdperson", cascade = CascadeType.ALL, optional = true)
    private Actor actor;

    public PersonDTO toDTO() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(this.id);
        personDTO.setName(this.name);
        personDTO.setGender(this.gender);
        if(getActor() != null){
            personDTO.setActor(true);
        } else{
            personDTO.setActor(false);
        }

        return personDTO;
    }

}
