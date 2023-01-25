package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.User;

public interface AuhenticationService {

    UserDto signInAndReturnJWT(UserDto signInRequest);
}
