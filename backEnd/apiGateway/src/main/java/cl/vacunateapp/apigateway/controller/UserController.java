package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("find/")
    public ResponseEntity<Optional<User>> findUserById(@RequestParam Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }


}