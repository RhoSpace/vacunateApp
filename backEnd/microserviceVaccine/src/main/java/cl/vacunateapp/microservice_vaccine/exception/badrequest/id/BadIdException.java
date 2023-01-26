package cl.vacunateapp.microservice_vaccine.exception.badrequest.id;

import cl.vacunateapp.microservice_vaccine.exception.badrequest.BadRequestException;

public class BadIdException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el id ingresado";


    public BadIdException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
