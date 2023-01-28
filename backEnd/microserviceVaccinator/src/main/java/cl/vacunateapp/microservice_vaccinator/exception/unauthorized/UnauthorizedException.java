package cl.vacunateapp.microservice_vaccinator.exception.unauthorized;

public class UnauthorizedException extends RuntimeException{

    public static final String DESCRIPTION = "Unauthorized Exception (401)";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
