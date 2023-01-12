package cl.vacunateapp.apigateway.exception.badrequest.name;

import cl.vacunateapp.apigateway.exception.badrequest.BadRequestException;

public class BadNameException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el nombre ingresado";


    public BadNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
