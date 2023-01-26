package cl.vacunateapp.microservice_vaccine.exception.forbidden;

public class ForbiddenException extends RuntimeException{

    public static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
