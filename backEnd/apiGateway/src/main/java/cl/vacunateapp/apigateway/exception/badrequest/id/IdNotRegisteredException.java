package cl.vacunateapp.apigateway.exception.badrequest.id;

import cl.vacunateapp.apigateway.exception.badrequest.rut.BadRutException;

public class IdNotRegisteredException extends BadIdException {
    public static final String DESCRIPTION = "No existe un usuario registrado con el id";

    public IdNotRegisteredException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
