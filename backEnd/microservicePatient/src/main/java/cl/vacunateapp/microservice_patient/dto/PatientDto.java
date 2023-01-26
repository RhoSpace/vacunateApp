package cl.vacunateapp.microservice_patient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class PatientDto {
    private Long id;

    private String rut;

    private String name;

    private String lastName;

    private String phone;

    private String email;

    private LocalDateTime creationDate;
}
