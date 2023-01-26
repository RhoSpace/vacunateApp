package cl.vacunateapp.microservice_patient.exception.badrequest.rut;

public class RutInvalidException extends BadRutException{
    public static final String DESCRIPTION = "El rut no es valido";

    public RutInvalidException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
