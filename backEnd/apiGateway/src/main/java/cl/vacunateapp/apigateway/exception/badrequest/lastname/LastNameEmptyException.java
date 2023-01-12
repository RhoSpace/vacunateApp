package cl.vacunateapp.apigateway.exception.badrequest.lastname;

public class LastNameEmptyException extends BadLastNameException {
    public static final String DESCRIPTION = "El Apellido no se encuentra en la peticion";

    public LastNameEmptyException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
