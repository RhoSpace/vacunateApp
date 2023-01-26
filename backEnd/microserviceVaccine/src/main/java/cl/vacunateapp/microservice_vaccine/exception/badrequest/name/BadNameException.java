package cl.vacunateapp.microservice_vaccine.exception.badrequest.name;

import cl.vacunateapp.microservice_vaccine.exception.badrequest.BadRequestException;

public class BadNameException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el nombre ingresado";


    public BadNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
