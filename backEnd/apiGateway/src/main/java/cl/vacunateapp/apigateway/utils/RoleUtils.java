package cl.vacunateapp.apigateway.utils;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.exception.badrequest.role.RoleNotExistException;

public class RoleUtils {

    public static Role checkRole (String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new RoleNotExistException(role);
        }
    }
}
