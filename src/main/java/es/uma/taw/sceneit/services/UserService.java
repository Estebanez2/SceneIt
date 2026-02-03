package es.uma.taw.sceneit.services;

import es.uma.taw.sceneit.dao.UserRepository;
import es.uma.taw.sceneit.dto.UserDTO;
import es.uma.taw.sceneit.entity.Role;
import es.uma.taw.sceneit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService extends ServiceParent<User, UserDTO>{
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleService roleService;

    public List<UserDTO> getAllUsersAsDTO() {
        List<User> user = userRepository.findAll();
        return convertToDTOList(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(Integer id) {
        return userRepository.findById(id)
                .map(User::toDTO)
                .orElse(new UserDTO());
    }

    public List<UserDTO> searchByName(String usuario) {
        return convertToDTOList(userRepository.buscarPorusuario(usuario));
    }

    public Integer save(UserDTO dto) {
        User entity = userRepository.findById(dto.getId()).orElse(new User());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPwd());
        entity.setFirstName(dto.getFirstName());
        entity.setSecondName1(dto.getSecondName1());
        entity.setSecondName2(dto.getSecondName2());

        String[] fecha = dto.getBirthDate().split("-");
        entity.setBirthDate(LocalDate.of(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));

        entity.setEmail(dto.getEmail());

        Role role = roleService.findEntityById(dto.getIdRole());
        entity.setRoleIdrole(role);

        userRepository.save(entity);

        return entity.getId();
    }

    public boolean comprobarUsuarioIgual (UserDTO user, Integer id){
        boolean esIgual = false;

        if(user != null && user.getId() == id){
            esIgual = true;
        }
        return esIgual;
    }

    public UserDTO loguear(String usuario, String pwd){
        User user = this.userRepository.logUsuario(usuario, pwd);

        if(user != null){
            return user.toDTO();
        } else{
            return null;
        }
    }
}
