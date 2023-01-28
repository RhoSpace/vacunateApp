package cl.vacunateapp.microservice_vaccinator.repository;

import cl.vacunateapp.microservice_vaccinator.entity.Role;
import cl.vacunateapp.microservice_vaccinator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar por rut
    Optional<User> findByRut(String rut);

    // Actualizar rol de usuario
    @Modifying
    @Query("update User set role=:role where rut=:rut")
    void updateUserRole(@Param("rut") String rut, @Param("role") Role role);

    // Buscar por id
    Optional<User> findUserById(Long id);

    // Numero de usuarios con el rol de USER
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'USER'")
    int countUsersByRoleUser();
}
