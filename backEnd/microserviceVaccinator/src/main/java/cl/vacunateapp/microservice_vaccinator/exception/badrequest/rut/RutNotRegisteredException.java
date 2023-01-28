package cl.vacunateapp.microservice_vaccinator.exception.badrequest.rut;

public class RutNotRegisteredException extends BadRutException{
    public static final String DESCRIPTION = "No existe un usuario registrado con el rut";

    public RutNotRegisteredException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
