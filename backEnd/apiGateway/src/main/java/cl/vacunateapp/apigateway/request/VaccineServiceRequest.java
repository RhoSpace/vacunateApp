package cl.vacunateapp.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "${vaccine.service.name}",
        path = "/api/vaccine",
//        url = "${vaccine.service.url}",
        configuration = FeignConfiguration.class
)
public interface VaccineServiceRequest {

    @PostMapping("/save")
    Object saveVacine(@RequestBody Object requestBody);

    @PutMapping("/update_count/")
    Object updateVaccineCount(@RequestParam Long id, @RequestBody Object requestBody);

    @PutMapping("/update_count_when_patient_vaccinated/")
    Object updateCountPatientVaccinated(@RequestParam Long id);

    @GetMapping("/list")
    List<Object> vaccineList();

}
