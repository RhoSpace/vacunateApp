package cl.vacunateapp.microservice_vaccine.exception.conflict.amount;

import cl.vacunateapp.microservice_vaccine.exception.conflict.ConflictException;

public class ConflictAmountException extends ConflictException {
    public static final String DESCRIPTION = "Conflicto con la cantidad de vacunas ingresada";

    public ConflictAmountException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
