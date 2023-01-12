package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.exception.badrequest.rut.RutRegisteredException;
import cl.vacunateapp.apigateway.repository.UserRepository;
import cl.vacunateapp.apigateway.utils.DtoUtils;
import cl.vacunateapp.apigateway.utils.RutUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
                .rut(userDto.getRut())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .creationDate(LocalDateTime.now())
                .role(Role.USER)
                .build();
        userRepository.save(userToSave);
        log.info("Usuario guardado con exito");

        return UserDto.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    // Metodo para buscar todos los usuarios
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Metodo para buscar usuarios por rut
    @Override
    public Optional<User> findByRut(String rut) {
        return userRepository.findByRut(rut);
    }

    // Metodo para buscar usuario por id
    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    // Metodo para borrar usuario
    @Override
    public void deleteUserById(Long id) {
        User userToDelete = userRepository.findUserById(id);
        userRepository.delete(userToDelete);
    }

    // Metodo para actualizar datos de un usuario
    @Override
    public User updateUserData(Long id, User user) {
        User userNoUpdate = userRepository.findUserById(id);

        userNoUpdate.setName(user.getName());
        userNoUpdate.setLastName(user.getLastName());
        userNoUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userNoUpdate.setPhone(user.getPhone());
        userNoUpdate.setEmail(user.getEmail());

        return userRepository.save(userNoUpdate);
    }

    // Metodo para obtener el numero total de usuarios con rol USER
    @Override
    public int getCountOfUserRole() {
        return userRepository.countUsersByRoleUser();
    }

    // Metodo para actualizar el rol del usuario
    @Override
    @Transactional
    public void changeRole(String rut, Role newRole) {
        userRepository.updateUserRole(rut, newRole);
    }

    public void checkRutRegistered(UserDto userDto) {
        if (userRepository.findByRut(userDto.getRut()).isPresent()) {
            throw new RutRegisteredException(userDto.getRut());
        }
    }

}