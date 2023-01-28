package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.request.PatientServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gateway/patient")
public class PatientController {

    @Autowired
    private PatientServiceRequest patientServiceRequest;

    @PostMapping("/save")
    public ResponseEntity<Object> savePatient(@RequestBody Object patient) {
        return new ResponseEntity<>(patientServiceRequest.savePatient(patient), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Object>> findAllPatient() {
        return new ResponseEntity<>(patientServiceRequest.findAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/find/id/")
    public ResponseEntity<Object> findPatientById(@RequestParam Long id) {
        return new ResponseEntity<>(patientServiceRequest.findPatientById(id), HttpStatus.OK);
    }

    @GetMapping("/find/rut/")
    public ResponseEntity<Object> findPatientByRut(@RequestParam String rut) {
        return new ResponseEntity<>(patientServiceRequest.findPatientByRut(rut), HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Object> updatePatientById(@RequestParam Long id, @RequestBody Object patientDto) {
        return new ResponseEntity<>(patientServiceRequest.updatePatientById(id, patientDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deletePatientById(@RequestParam Long id) {
        return new ResponseEntity<>(patientServiceRequest.deletePatientById(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count/patient")
    public ResponseEntity<Integer> getCountOfPatient() {
        return new ResponseEntity<>(patientServiceRequest.getCountOfPatient(), HttpStatus.OK);
    }
}
