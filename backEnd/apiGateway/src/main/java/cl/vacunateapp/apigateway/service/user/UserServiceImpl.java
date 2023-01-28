package cl.vacunateapp.apigateway.service.user;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.exception.badrequest.id.IdNotRegisteredException;
import cl.vacunateapp.apigateway.exception.badrequest.rut.RutNotRegisteredException;
import cl.vacunateapp.apigateway.exception.conflict.rut.RutRegisteredException;
import cl.vacunateapp.apigateway.repository.UserRepository;
import cl.vacunateapp.apigateway.utils.DtoUtils;
import cl.vacunateapp.apigateway.utils.IdUtils;
import cl.vacunateapp.apigateway.utils.RoleUtils;
import cl.vacunateapp.apigateway.utils.RutUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Metodo para guardar usuario
    @Override
    public UserDto saveUser(UserDto userDto) {
        //Compruebo si el rut ya esta registrado
        checkRutRegistered(userDto);

        User userToSave = User.builder()
                .rut(RutUtils.checkValidRut(userDto.getRut()))
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .creationDate(LocalDateTime.now())
                .role(Role.USER)
                .build();
        userRepository.save(userToSave);

        return UserDto.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    // Metodo para buscar usuarios por rut
    @Override
    public User findByRut(String rut) {
        DtoUtils.checkEmptyNullRut(rut);
        RutUtils.checkValidRut(rut);

        return userRepository.findByRut(rut).orElseThrow(() -> new RutNotRegisteredException(rut));
    }

    // Metodo para buscar usuario por id
    @Override
    public User findUserById(Long id) {
        IdUtils.checkId(id);
        return userRepository.findUserById(id)
                .orElseThrow(() -> new IdNotRegisteredException(id.toString()));
    }

    // Metodo para actualizar el rol del usuario
    @Override
    @Transactional
    public void changeRole(String rut, String role) {
        DtoUtils.checkEmptyNullRut(rut);
        RutUtils.checkValidRut(rut);
        Role newRole = RoleUtils.checkRole(role);

        userRepository.updateUserRole(rut, newRole);
    }

    // Metodos auxiliares

    public void checkRutRegistered(UserDto userDto) {
        userDto.setRut(RutUtils.checkValidRut(userDto.getRut()));

        if (userRepository.findByRut(userDto.getRut()).isPresent()) {
            throw new RutRegisteredException(userDto.getRut());
        }
    }
}