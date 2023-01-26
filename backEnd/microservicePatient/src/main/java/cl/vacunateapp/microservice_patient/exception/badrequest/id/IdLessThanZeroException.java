package cl.vacunateapp.microservice_patient.exception.badrequest.id;

public class IdLessThanZeroException extends BadIdException {
    public static final String DESCRIPTION = "El id ingresado es menor o igual a cero";

    public IdLessThanZeroException(String detail) {
        super(DESCRIPTION + ": id -> " + detail);
    }
}
