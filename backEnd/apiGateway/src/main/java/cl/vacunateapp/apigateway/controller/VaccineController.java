package cl.vacunateapp.apigateway.controller;

import cl.vacunateapp.apigateway.request.VaccineServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gateway/vaccine")
public class VaccineController {

    @Autowired
    VaccineServiceRequest vaccineServiceRequest;

    @PostMapping("/save")
    public ResponseEntity<Object> saveVacine(@RequestBody Object vaccine) {
        return new ResponseEntity<>(vaccineServiceRequest.saveVacine(vaccine), HttpStatus.CREATED);
    }

    @PutMapping("/update_count/")
    public ResponseEntity<Object> updateVaccineCount(@RequestParam Long id, @RequestBody Object vaccineDto) {
        return new ResponseEntity<>(vaccineServiceRequest.updateVaccineCount(id, vaccineDto), HttpStatus.OK);
    }

    @PutMapping("/update_count_when_patient_vaccinated/")
    public ResponseEntity<Object> updateCountPatientVaccinated(@RequestParam Long id) {
        return new ResponseEntity<>(vaccineServiceRequest.updateCountPatientVaccinated(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Object>> vaccineList() {
        return new ResponseEntity<>(vaccineServiceRequest.vaccineList(), HttpStatus.OK);
    }
}
