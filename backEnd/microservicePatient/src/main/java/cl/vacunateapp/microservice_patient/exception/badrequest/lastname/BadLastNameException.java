package cl.vacunateapp.microservice_patient.exception.badrequest.lastname;

import cl.vacunateapp.microservice_patient.exception.badrequest.BadRequestException;

public class BadLastNameException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con el Apellido ingresado";


    public BadLastNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
