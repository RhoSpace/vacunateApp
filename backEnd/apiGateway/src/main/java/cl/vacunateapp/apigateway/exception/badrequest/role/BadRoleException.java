package cl.vacunateapp.apigateway.exception.badrequest.role;

import cl.vacunateapp.apigateway.exception.badrequest.BadRequestException;

public class BadRoleException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el rol ingresado";
    public BadRoleException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
