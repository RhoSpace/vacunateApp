package cl.vacunateapp.microservice_vaccine.exception.badrequest.name;

public class NameEmptyException extends BadNameException{
    public static final String DESCRIPTION = "El nombre no se encuentra en la peticion";

    public NameEmptyException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
