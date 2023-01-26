package cl.vacunateapp.microservice_vaccine.controller;

import cl.vacunateapp.microservice_vaccine.dto.VaccineDto;
import cl.vacunateapp.microservice_vaccine.entity.Vaccine;
import cl.vacunateapp.microservice_vaccine.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vaccine")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    // Guardar una vacuna
    @PostMapping("/save")
    public ResponseEntity<VaccineDto> savePatient(@RequestBody VaccineDto vaccineDto) {
        return new ResponseEntity<>(vaccineService.createVaccine(vaccineDto), HttpStatus.CREATED);
    }

    // Actualizar conteo de vacunas
    @PutMapping("/update_count")
    public ResponseEntity<VaccineDto> changeRole(@RequestParam Long id, @RequestBody VaccineDto vaccineDto) {
        return new ResponseEntity<>(vaccineService.updateVaccineCount(id, vaccineDto), HttpStatus.OK);
    }

    // Actualizar conteo de vacunas cuando se vacuna un paciente
    @PutMapping("/update_count_when_pacient_vaccinated")
    public ResponseEntity<VaccineDto> changeRole(@RequestParam Long id) {
        return new ResponseEntity<>(vaccineService.updateVaccineNumberWhenAPatientIsVaccinated(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vaccine>> vaccineList() {
        return new ResponseEntity<>(vaccineService.vaccineList(), HttpStatus.OK);
    }
}
