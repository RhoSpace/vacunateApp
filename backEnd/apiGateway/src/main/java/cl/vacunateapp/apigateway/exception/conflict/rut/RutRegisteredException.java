package cl.vacunateapp.apigateway.exception.conflict.rut;


public class RutRegisteredException extends ConflictRutException {

    public static final String DESCRIPTION = "Ya existe un usuario registrado con el rut";

    public RutRegisteredException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
