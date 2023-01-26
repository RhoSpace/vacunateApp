package cl.vacunateapp.microservice_patient.exception.badrequest.rut;

public class RutEmptyException extends BadRutException{
    public static final String DESCRIPTION = "El rut no se encuentra en la peticion";

    public RutEmptyException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
