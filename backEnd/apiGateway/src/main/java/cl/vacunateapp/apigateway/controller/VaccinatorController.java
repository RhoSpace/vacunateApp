package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.request.VaccinatorServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gateway/vaccinator")
public class VaccinatorController {

    @Autowired
    private VaccinatorServiceRequest vaccinatorServiceRequest;

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody Object user) {
        return new ResponseEntity<>(vaccinatorServiceRequest.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Object>> findAllUsers() {
        return new ResponseEntity<>(vaccinatorServiceRequest.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/find/id/")
    public ResponseEntity<Object> findUserById(@RequestParam Long id) {
        return new ResponseEntity<>(vaccinatorServiceRequest.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/find/rut/")
    public ResponseEntity<Object> findUserByRut(@RequestParam String rut) {
        return new ResponseEntity<>(vaccinatorServiceRequest.findUserByRut(rut), HttpStatus.OK);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam Long id) {
        return new ResponseEntity<>(vaccinatorServiceRequest.deleteUserById(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/")
    public ResponseEntity<Object> updateUserById(@RequestParam Long id, @RequestBody Object userDto) {
        return new ResponseEntity<>(vaccinatorServiceRequest.updateUserById(id, userDto), HttpStatus.OK);
    }

    @GetMapping("/count/user")
    public ResponseEntity<Integer> getCountOfUserRole() {
        return new ResponseEntity<>(vaccinatorServiceRequest.getCountOfUserRole(), HttpStatus.OK);
    }
}
