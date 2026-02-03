package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable, toDTO<RoleDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRole", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "roleIdrole")
    private Set<User> users = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "Permisos_idPermisos", nullable = false)
    private Permisos permisosIdpermisos;


    @Override
    public RoleDTO toDTO() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(this.id);
        roleDTO.setName(this.name);

        return roleDTO;
    }

}
