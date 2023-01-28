package cl.vacunateapp.microservice_vaccine.exception.conflict.amount;

public class AmountEqualZeroException extends ConflictAmountException {
    public static final String DESCRIPTION = "La cantidad de vacunas es cero";

    public AmountEqualZeroException(String detail) {
        super(DESCRIPTION + ": se intento restar una vacuna, la cantidad de vacunas actual es -> " + detail);
    }
}
