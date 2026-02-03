package es.uma.taw.sceneit.entity;

import es.uma.taw.sceneit.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User  implements Serializable, toDTO<UserDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_idRole")
    private Role roleIdrole;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "second_name_1", length = 45)
    private String secondName1;

    @Column(name = "second_name_2", length = 45)
    private String secondName2;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "userIduser")
    private Set<Interaction> interactions = new LinkedHashSet<>();

    @Override
    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setPwd(this.password);
        userDTO.setFirstName(this.firstName);
        userDTO.setSecondName1(this.secondName1);
        userDTO.setSecondName2(this.secondName2);
        userDTO.setEmail(this.email);
        userDTO.setBirthDate(String.format("%04d-%02d-%02d",
                birthDate.getYear(),
                birthDate.getMonthValue(),
                birthDate.getDayOfMonth()
        ));
        userDTO.setIdRole(this.roleIdrole.getId());
        userDTO.setNombreRole(this.roleIdrole.getName());
        userDTO.setAdmin(roleIdrole.getPermisosIdpermisos().getEscribirAdmin() != 0);
        userDTO.setEditor(roleIdrole.getPermisosIdpermisos().getEscribirEditor() != 0);
        userDTO.setEstadista(roleIdrole.getPermisosIdpermisos().getEscribirEstadista() != 0);
        userDTO.setUserEscritura(roleIdrole.getPermisosIdpermisos().getEscribirUsuario() != 0);
        userDTO.setUserLectura(roleIdrole.getPermisosIdpermisos().getLeerUsuario() != 0);

        return userDTO;
    }

}