package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo para guardar usuario
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreationDate(LocalDateTime.now());

        return userRepository.save(user);
    }

    // Metodo para buscar usuarios por rut
    @Override
    public Optional<User> findByRut(String rut) {
        return userRepository.findByRut(rut);
    }

    // Metodo para actualizar el rol del usuario
    @Override
    @Transactional
    public void changeRole(String rut, Role newRole) {
        userRepository.updateUserRole(rut, newRole);
    }

    // Metodo para obtener el numero total de usuarios con el rol USER
    @Override
    public int getCountByRole_User() {
        return userRepository.countByRole_User();
    }
}
