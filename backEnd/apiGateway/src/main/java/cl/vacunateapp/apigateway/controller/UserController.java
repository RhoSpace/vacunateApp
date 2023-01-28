package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Cambiar el rol del usuario
    @PutMapping("/change")
    public ResponseEntity<String> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String role) {
        userService.changeRole(userPrincipal.getRut(), role);
        return new ResponseEntity<>("Rol Actualizado", HttpStatus.OK);
    }
}