package cl.vacunateapp.microservice_patient.exception.badrequest.name;

import cl.vacunateapp.microservice_patient.exception.badrequest.BadRequestException;

public class BadNameException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el nombre ingresado";


    public BadNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
