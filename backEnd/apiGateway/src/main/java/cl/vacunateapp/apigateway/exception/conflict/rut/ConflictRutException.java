package cl.vacunateapp.apigateway.exception.conflict.rut;

import cl.vacunateapp.apigateway.exception.conflict.ConflictException;

public class ConflictRutException extends ConflictException {

    public static final String DESCRIPTION = "Conflicto con el rut ingresado";


    public ConflictRutException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
