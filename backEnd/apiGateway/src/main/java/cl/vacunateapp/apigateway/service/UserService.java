package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Metodo para guardar usuario
    User saveUser(User user);

    // Metodo para buscar todos los usuarios
    List<User> findAllUsers();

    //Metodo para buscar usuarios por rut
    Optional<User> findByRut(String rut);

    // Metodo para buscar usuario por id
    Optional<User> findUserById(Long id);

    // Metodo para borrar usuario
    void deleteUserById(Long id);

    //Metodo para actualizar el rol del usuario
    void changeRole(String rut, Role newRole);

    // Metodo para obtener el numero total de usuarios con el rol USER

}
