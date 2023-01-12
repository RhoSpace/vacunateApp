package cl.vacunateapp.apigateway.exception.badrequest.rut;


public class RutRegisteredException extends BadRutException {

    public static final String DESCRIPTION = "Existe un usuario registrado con el rut";

    public RutRegisteredException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
