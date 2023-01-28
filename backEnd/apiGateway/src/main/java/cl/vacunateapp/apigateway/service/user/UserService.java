package cl.vacunateapp.apigateway.service.user;


import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    // Metodo para guardar usuario
    UserDto saveUser(UserDto userDto);

    // Metodo para buscar usuarios por rut
    User findByRut(String rut);

    // Metodo para buscar usuario por id
    User findUserById(Long id);

    // Metodo para actualizar el rol del usuario
    @Transactional
    void changeRole(String rut, String role);
}
