package cl.vacunateapp.apigateway.utils;

import cl.vacunateapp.apigateway.exception.badrequest.id.IdEmptyException;
import cl.vacunateapp.apigateway.exception.badrequest.id.IdLessThanZeroException;

public class IdUtils {

    public static void validateId(Long id) {
        if (id == null) {
            throw new IdEmptyException("Campo de id nulo");
        } else if (id.toString().isEmpty()) {
            throw new IdEmptyException("Campo de id vacio");
        }

        if (id <= 0) {
            throw new IdLessThanZeroException(id.toString());
        }
    }
}
