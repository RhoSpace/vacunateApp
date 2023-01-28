package cl.vacunateapp.microservice_vaccine.exception.badrequest.amount;

public class AmountLessThanZeroException extends BadAmountException {
    public static final String DESCRIPTION = "La cantidad de vacunas es menor o igual a cero";

    public AmountLessThanZeroException(String detail) {
        super(DESCRIPTION + ": vacunas que se intentaron ingresar -> " + detail);
    }
}
