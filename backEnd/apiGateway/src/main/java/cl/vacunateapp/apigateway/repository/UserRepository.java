package cl.vacunateapp.apigateway.repository;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Query personalizada
    Optional<User> findByRut(String rut);

    @Modifying
    @Query("update User set role=:role where rut=:rut")
    void updateUserRole(@Param("RUT") String rut, @Param("ROLE") Role role);

}
