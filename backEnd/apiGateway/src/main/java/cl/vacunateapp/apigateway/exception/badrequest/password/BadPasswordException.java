package cl.vacunateapp.apigateway.exception.badrequest.password;

import cl.vacunateapp.apigateway.exception.badrequest.BadRequestException;

public class BadPasswordException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con La contraseña ingresada";
    public BadPasswordException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
