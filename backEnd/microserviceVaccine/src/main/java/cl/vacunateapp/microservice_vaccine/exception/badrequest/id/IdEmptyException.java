package cl.vacunateapp.microservice_vaccine.exception.badrequest.id;

public class IdEmptyException extends BadIdException {
    public static final String DESCRIPTION = "El id no se encuentra en la peticion";

    public IdEmptyException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
