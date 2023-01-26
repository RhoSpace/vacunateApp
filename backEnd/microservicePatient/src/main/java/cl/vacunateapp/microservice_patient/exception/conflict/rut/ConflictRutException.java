package cl.vacunateapp.microservice_patient.exception.conflict.rut;

import cl.vacunateapp.microservice_patient.exception.conflict.ConflictException;

public class ConflictRutException extends ConflictException {

    public static final String DESCRIPTION = "Conflicto con el rut ingresado";


    public ConflictRutException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
