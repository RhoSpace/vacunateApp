package cl.vacunateapp.microservice_vaccine.utils;

import cl.vacunateapp.microservice_vaccine.exception.badrequest.amount.AmountLessThanZeroException;
import cl.vacunateapp.microservice_vaccine.exception.conflict.amount.AmountEqualZeroException;

public class AmountUtils {

    public static void checkAmountLessThanZero(Integer amount) {
        if (amount <= 0) {
            throw new AmountLessThanZeroException(amount.toString());
        }
    }

    public static void checkAmountEqualZero(Integer amount) {
        if (amount == 0) {
            throw new AmountEqualZeroException(amount.toString());
        }
    }
}
