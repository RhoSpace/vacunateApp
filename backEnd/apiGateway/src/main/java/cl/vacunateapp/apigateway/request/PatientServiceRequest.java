package cl.vacunateapp.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(
        value = "${patient.service.name}",
        path = "/api/patient",
//        url = "${patient.service.url}",
        configuration = FeignConfiguration.class
)
public interface PatientServiceRequest {

    @PostMapping("/save")
    Object savePatient(@RequestBody Object requestBody);

    @GetMapping("/list")
    List<Object> findAllPatient();

    @GetMapping("/find/id/")
    Object findPatientById(@RequestParam Long id);

    @GetMapping("/find/rut/")
    Object findPatientByRut(@RequestParam String rut);

    @PutMapping("/update/")
    Object updatePatientById(@RequestParam Long id, @RequestBody Object requestBody);

    @DeleteMapping("/delete/")
    HttpStatus deletePatientById(@RequestParam Long id);

    @GetMapping("/count/patient")
    Integer getCountOfPatient();
}
