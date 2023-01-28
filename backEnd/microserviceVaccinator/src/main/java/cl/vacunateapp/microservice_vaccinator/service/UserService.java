package cl.vacunateapp.microservice_vaccinator.service;

import cl.vacunateapp.microservice_vaccinator.dto.UserDto;
import cl.vacunateapp.microservice_vaccinator.entity.User;

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
    UserDto updateUserData(Long id, UserDto userDto);

    // Metodo para obtener el numero total de usuarios con rol USER
    int getCountOfUserRole();
}
