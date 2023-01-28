package cl.vacunateapp.microservice_vaccinator.controller;

import cl.vacunateapp.microservice_vaccinator.dto.UserDto;
import cl.vacunateapp.microservice_vaccinator.entity.User;
import cl.vacunateapp.microservice_vaccinator.service.UserService;
import cl.vacunateapp.microservice_vaccinator.utils.DtoUtils;
import cl.vacunateapp.microservice_vaccinator.utils.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vaccinator")
public class UserController {

    @Autowired
    private UserService userService;

    // Crear usuario
    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        DtoUtils.checkDto(userDto);
        RutUtils.checkValidRut(userDto.getRut());

        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
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