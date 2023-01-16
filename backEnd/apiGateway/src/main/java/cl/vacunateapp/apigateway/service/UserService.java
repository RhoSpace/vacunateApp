package cl.vacunateapp.apigateway.service;


import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {


    // Metodo para guardar usuario
    UserDto saveUser(UserDto userDto);

    // Metodo para buscar todos los usuarios
    List<UserDto> findAllUsers();

    // Metodo para buscar usuarios por rut
    User findByRut(String rut);

    // Metodo para buscar usuario por id
    User findUserById(Long id);

    // Metodo para borrar usuario
    void deleteUserById(Long id);

    // Metodo para actualizar datos de un usuario
    User updateUserData(Long id, User user);

    // Metodo para obtener el numero total de usuarios con rol USER
    int getCountOfUserRole();

    // Metodo para actualizar el rol del usuario
    @Transactional
    void changeRole(String rut, String role);
}
