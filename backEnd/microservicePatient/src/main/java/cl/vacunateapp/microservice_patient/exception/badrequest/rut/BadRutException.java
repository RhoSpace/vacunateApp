package cl.vacunateapp.microservice_patient.exception.badrequest.rut;

import cl.vacunateapp.microservice_patient.exception.badrequest.BadRequestException;

public class BadRutException extends BadRequestException {

    public static final String DESCRIPTION = "Problemas con el rut ingresado";


    public BadRutException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
