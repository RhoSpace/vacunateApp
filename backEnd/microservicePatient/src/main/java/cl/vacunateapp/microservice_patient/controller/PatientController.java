package cl.vacunateapp.microservice_patient.controller;

import cl.vacunateapp.microservice_patient.dto.PatientDto;
import cl.vacunateapp.microservice_patient.entity.Patient;
import cl.vacunateapp.microservice_patient.service.PatientService;
import cl.vacunateapp.microservice_patient.utils.DtoUtils;
import cl.vacunateapp.microservice_patient.utils.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Guardar un paciente
    @PostMapping("/save")
    public ResponseEntity<PatientDto> signUp(@RequestBody PatientDto patientDto) {
        DtoUtils.checkDto(patientDto);
        RutUtils.checkValidRut(patientDto.getRut());

        return new ResponseEntity<>(patientService.savePatient(patientDto), HttpStatus.CREATED);
    }

    // Listar todos los pacientes
    @GetMapping("/list")
    public ResponseEntity<List<PatientDto>> findAllUsers() {
        return new ResponseEntity<>(patientService.findAllPatients(), HttpStatus.OK);
    }

    // Buscar paciente por id
    @GetMapping("/find/id/")
    public ResponseEntity<PatientDto> findUserById(@RequestParam Long id) {
        Patient user = patientService.findPatientById(id);

        return new ResponseEntity<>(PatientDto.builder()
                .rut(user.getRut())
                .name(user.getName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build(), HttpStatus.OK);
    }

    // Buscar usuario por rut
    @GetMapping("/find/rut/")
    public ResponseEntity<PatientDto> findUserByRut(@RequestParam String rut) {
        //Compruebo si el rut es valido y cargo el usuario
        Patient user = patientService.findPatientByRut(RutUtils.checkValidRut(rut));

        return new ResponseEntity<>(PatientDto.builder()
                .rut(user.getRut())
                .name(user.getName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build(), HttpStatus.OK);
    }

    // Actualizar datos del paciente segun el id
    @PutMapping("/update/")
    public ResponseEntity<PatientDto> updateUserById(@RequestParam Long id, @RequestBody PatientDto patientDto) {
        return new ResponseEntity<>(patientService.updatePatientData(id, patientDto),HttpStatus.OK);
    }

    // Eliminar usuario segun id
    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam Long id) {
        patientService.deletePatientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
