package cl.vacunateapp.microservice_vaccine.exception.badrequest.amount;

import cl.vacunateapp.microservice_vaccine.exception.badrequest.BadRequestException;

public class BadAmountException extends BadRequestException {
    public static final String DESCRIPTION = "Problemas con la cantidad de vacunas ingresada";
    
    public BadAmountException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
