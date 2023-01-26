package cl.vacunateapp.microservice_patient.exception.conflict;

public class ConflictException extends RuntimeException{

    public static final String DESCRIPTION = "Conflict Exception (409)";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
