package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.repository.UserRepository;
import cl.vacunateapp.apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    // Metodo para guardar usuario
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreationDate(LocalDateTime.now());

        User userCreated = userRepository.save(user);
        String tokenJwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(tokenJwt);

        return userCreated;
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
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // Metodo para borrar usuario
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Metodo para actualizar datos de un usuario
    // Metodo para obtener el numero total de usuarios
    // Metodos Auxiliares

    // Metodo para actualizar el rol del usuario
    @Override
    @Transactional
    public void changeRole(String rut, Role newRole) {
        userRepository.updateUserRole(rut, newRole);
    }

    // Metodo para obtener el numero total de usuarios con el rol USER

}
