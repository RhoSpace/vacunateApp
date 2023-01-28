package cl.vacunateapp.apigateway.service.authentication;

import cl.vacunateapp.apigateway.dto.UserDto;

public interface AuthenticationService {

    UserDto signInAndReturnJWT(UserDto signInRequest);
}
