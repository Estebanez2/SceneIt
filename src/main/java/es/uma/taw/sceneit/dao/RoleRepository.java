package es.uma.taw.sceneit.dao;

import es.uma.taw.sceneit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
