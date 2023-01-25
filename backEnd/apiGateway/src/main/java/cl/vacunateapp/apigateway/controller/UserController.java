package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.repository.UserRepository;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.service.UserService;
import cl.vacunateapp.apigateway.utils.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Cambiar el rol del usuario
    @PutMapping("/change")
    public ResponseEntity<String> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String role) {
        userService.changeRole(userPrincipal.getRut(), role);
        return new ResponseEntity<>("Rol Actualizado", HttpStatus.OK);
    }

    // Listar todos los usuarios
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    // Buscar usuario por id
    @GetMapping("/find/id/")
    public ResponseEntity<UserDto> findUserById(@RequestParam Long id) {
        User user = userService.findUserById(id);

        return new ResponseEntity<>(UserDto.builder()
                .rut(user.getRut())
                .name(user.getName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build(), HttpStatus.OK);
    }

    // Buscar usuario por rut
    @GetMapping("/find/rut/")
    public ResponseEntity<UserDto> findUserByRut(@RequestParam String rut) {
        //Compruebo si el rut es valido y cargo el usuario
        User user = userService.findByRut(RutUtils.checkValidRut(rut));

        return new ResponseEntity<>(UserDto.builder()
                .rut(user.getRut())
                .name(user.getName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build(), HttpStatus.OK);
    }

    // Eliminar usuario segun id
    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar datos del usuario segun el id
    @PutMapping("/update/")
    public ResponseEntity<UserDto> updateUserById(@RequestParam Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUserData(id, userDto),HttpStatus.OK);
    }

    // Obtener el numero de usuarios con el rol USUARIO
    @GetMapping("/count/user")
    public ResponseEntity<Integer> getCountOfUserRole() {
        return new ResponseEntity<>(userService.getCountOfUserRole(), HttpStatus.OK);
    }
}