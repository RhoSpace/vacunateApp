package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Cambiar el rol del usuario
    @PutMapping("/change")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam Role role) {
        userService.changeRole(userPrincipal.getRut(), role);

        return ResponseEntity.ok(true);
    }

    // Listar todos los usuarios
    @GetMapping("/list")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // Buscar usuario por id
    @GetMapping("/find/")
    public ResponseEntity<User> findUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    // Eliminar usuario segun id
    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar datos del usuario segun el id
    @PutMapping("/update/")
    public ResponseEntity<User> updateUserById(@RequestParam Long id, @RequestBody User user) {
        userService.updateUserData(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}