package cl.vacunateapp.microservice_vaccine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class VaccineDto {
    private Long id;

    private String name;

    private Integer amount;
}
