package cl.vacunateapp.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "${vaccinator.service.name}",
        path = "/api/vaccinator",
//        url = "${vaccinator.service.url}",
        configuration = FeignConfiguration.class
)
public interface VaccinatorServiceRequest {

    @PostMapping("/save")
    Object saveUser(@RequestBody Object requestBody);

    @GetMapping("/list")
    List<Object> findAllUsers();

    @GetMapping("/find/id/")
    Object findUserById(@RequestParam Long id);

    @GetMapping("/find/rut/")
    Object findUserByRut(@RequestParam String rut);

    @DeleteMapping("/delete/")
    HttpStatus deleteUserById(@RequestParam Long id);

    @PutMapping("/update/")
    Object updateUserById(@RequestParam Long id, @RequestBody Object requestBody);

    @GetMapping("/count/user")
    Integer getCountOfUserRole();
}
