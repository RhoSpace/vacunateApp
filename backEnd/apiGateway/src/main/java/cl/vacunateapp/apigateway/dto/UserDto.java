package cl.vacunateapp.apigateway.dto;

import cl.vacunateapp.apigateway.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class UserDto {
    private Long id;

    private String rut;

    private String name;

    private String lastName;

    private String password;

    private String phone;

    private String email;

    private LocalDateTime creationDate;

    private Role role;

    private String token;
}
