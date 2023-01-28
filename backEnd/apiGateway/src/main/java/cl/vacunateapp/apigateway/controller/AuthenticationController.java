package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.service.authentication.AuthenticationService;
import cl.vacunateapp.apigateway.service.user.UserService;
import cl.vacunateapp.apigateway.utils.DtoUtils;
import cl.vacunateapp.apigateway.utils.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
        DtoUtils.checkDto(userDto);
        RutUtils.checkValidRut(userDto.getRut());

        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(userDto), HttpStatus.OK);
    }
}