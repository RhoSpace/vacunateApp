package cl.vacunateapp.microservice_patient.utils;

import cl.vacunateapp.microservice_patient.exception.badrequest.id.IdEmptyException;
import cl.vacunateapp.microservice_patient.exception.badrequest.id.IdLessThanZeroException;

public class IdUtils {

    public static void checkId(Long id) {
        checkIdEmptyNull(id);
        checkIdLessThanZero(id);
        }

    private static void checkIdEmptyNull(Long id) {
        if (id == null) {
            throw new IdEmptyException("Campo de id nulo");
        }
    }

    private static void checkIdLessThanZero(Long id) {
        if (id <= 0) {
            throw new IdLessThanZeroException(id.toString());
        }
    }
}
