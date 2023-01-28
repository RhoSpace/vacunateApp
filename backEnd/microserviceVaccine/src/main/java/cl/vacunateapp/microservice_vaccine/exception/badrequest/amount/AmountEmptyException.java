package cl.vacunateapp.microservice_vaccine.exception.badrequest.amount;

public class AmountEmptyException extends BadAmountException {
    public static final String DESCRIPTION = "El numero de vacunas no se encuentra en la peticion";

    public AmountEmptyException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
