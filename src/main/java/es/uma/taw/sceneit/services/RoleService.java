package es.uma.taw.sceneit.services;


import es.uma.taw.sceneit.dao.RoleRepository;
import es.uma.taw.sceneit.dto.RoleDTO;
import es.uma.taw.sceneit.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends ServiceParent<Role, RoleDTO>{
    @Autowired
    RoleRepository roleRepository;

    public List<RoleDTO> getAllUsersAsDTO() {
        List<Role> role = roleRepository.findAll();
        return convertToDTOList(role);
    }

    public Role findEntityById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
