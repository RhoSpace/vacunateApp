package cl.vacunateapp.microservice_vaccinator.exception.badrequest.role;

public class RoleNotExistException extends BadRoleException {
    public static final String DESCRIPTION = "El rol ingresado no existe";

    public RoleNotExistException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
