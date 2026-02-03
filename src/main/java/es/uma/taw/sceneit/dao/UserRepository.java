package es.uma.taw.sceneit.dao;
import es.uma.taw.sceneit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where lower(u.username) like lower(concat('%', :usuario, '%'))")
    List<User> buscarPorusuario (@Param("usuario") String usuario);

    @Query("select u from User u where u.username = :usuario and u.password = :pwd")
    User logUsuario (@Param("usuario") String usuario, @Param("pwd") String pwd);
}
