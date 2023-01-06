package cl.vacunateapp.apigateway.service;

import cl.vacunateapp.apigateway.entity.User;

public interface AuhenticationService {
    User signInAndReturnJWT(User signInRequest);
}
