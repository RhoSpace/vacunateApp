package cl.vacunateapp.apigateway.exception.badrequest.rut;

public class RutInvalidException extends BadRutException{
    public static final String DESCRIPTION = "El rut no es valido";

    public RutInvalidException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
