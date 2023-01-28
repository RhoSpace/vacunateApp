package cl.vacunateapp.microservice_vaccinator.utils;

import cl.vacunateapp.microservice_vaccinator.entity.Role;
import cl.vacunateapp.microservice_vaccinator.exception.badrequest.role.RoleNotExistException;

public class RoleUtils {

    public static Role checkRole (String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new RoleNotExistException(role);
        }
    }
}
