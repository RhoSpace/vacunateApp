package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    // Metodo para buscar todos los usuarios
    List<User> findAllUsers();

    //Metodo para buscar usuarios por rut
    Optional<User> findByRut(String rut);

    //Metodo para actualizar el rol del usuario
    void changeRole(String rut, Role newRole);

    // Metodo para obtener el numero total de usuarios con el rol USER

}
