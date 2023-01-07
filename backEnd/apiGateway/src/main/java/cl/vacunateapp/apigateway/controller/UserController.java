package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/change")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam Role role) {
        userService.changeRole(userPrincipal.getRut(), role);

        return ResponseEntity.ok(true);
    }
}
