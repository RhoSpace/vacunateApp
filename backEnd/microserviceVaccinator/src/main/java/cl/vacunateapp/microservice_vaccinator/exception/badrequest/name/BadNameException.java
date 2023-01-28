package cl.vacunateapp.microservice_vaccinator.exception.badrequest.name;

import cl.vacunateapp.microservice_vaccinator.exception.badrequest.BadRequestException;

public class BadNameException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el nombre ingresado";


    public BadNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
