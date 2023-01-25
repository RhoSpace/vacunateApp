package cl.vacunateapp.apigateway.service;

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

    // Metodo para buscar todos los usuarios
    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> UserDto.builder()
                        .rut(user.getRut())
                        .name(user.getName())
                        .lastName(user.getLastName())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .toList();
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

    // Metodo para borrar usuario
    @Override
    public void deleteUserById(Long id) {
        User userToDelete = findUserById(id);
        userRepository.delete(userToDelete);
    }

    // Metodo para actualizar datos de un usuario
    @Override
    public UserDto updateUserData(Long id, UserDto userDto) {
        //Cargo al usuario que se intenta actualizar
        User userNoUpdate = findUserById(id);

        //compruebo que datos se van a actualizar
        Optional.ofNullable(userDto.getName()).ifPresent(userNoUpdate::setName);
        Optional.ofNullable(userDto.getLastName()).ifPresent(userNoUpdate::setLastName);
        Optional.ofNullable(userDto.getPassword())
                .ifPresent(password -> userNoUpdate.setPassword(passwordEncoder.encode(password)));
        Optional.ofNullable(userDto.getPhone()).ifPresent(userNoUpdate::setPhone);
        Optional.ofNullable(userDto.getEmail()).ifPresent(userNoUpdate::setEmail);

        userRepository.save(userNoUpdate);

        return UserDto.builder()
                .rut(userNoUpdate.getRut())
                .name(userNoUpdate.getName())
                .lastName(userNoUpdate.getLastName())
                .phone(userNoUpdate.getPhone())
                .email(userNoUpdate.getEmail())
                .role(userNoUpdate.getRole())
                .build();
    }

    // Metodo para obtener el numero total de usuarios con rol USER
    @Override
    public int getCountOfUserRole() {
        return userRepository.countUsersByRoleUser();
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
        if (userRepository.findByRut(userDto.getRut()).isPresent()) {
            throw new RutRegisteredException(userDto.getRut());
        }
    }
}