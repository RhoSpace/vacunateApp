package cl.vacunateapp.apigateway.utils;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.exception.badrequest.lastname.LastNameEmptyException;
import cl.vacunateapp.apigateway.exception.badrequest.name.BadNameException;
import cl.vacunateapp.apigateway.exception.badrequest.password.PasswordEmptyException;
import cl.vacunateapp.apigateway.exception.badrequest.rut.RutEmptyException;

public class DtoUtils {
    public static void checkEmptyNullRut(String rut) {
        //Compruebo si el rut existe en el dto
        if (rut == null) {
            throw new RutEmptyException("Campo de rut nulo");
        } else if (rut.isEmpty()) {
            throw new RutEmptyException("Campo de Rut vacio");
        }
    }

    public static void checkEmptyNullName(UserDto userDto) {
        //Compruebo si el nombre existe en el dto
        if (userDto.getName() == null) {
            throw new BadNameException("Campo de Nombre nulo");
        } else if (userDto.getName().isEmpty()) {
            throw new BadNameException("Campo de Nombre vacio");
        }
    }

    public static void checkEmptyNullLastName(UserDto userDto) {
        //Compruebo si el apellido existe en el dto
        if (userDto.getLastName() == null) {
            throw new LastNameEmptyException("Campo de Apellido nulo");
        } else if (userDto.getLastName().isEmpty()) {
            throw new LastNameEmptyException("Campo de Apellido vacio");
        }
    }

    public static void checkEmptyNullPassword(UserDto userDto) {
        //Compruebo si la contraseña existe en el dto
        if (userDto.getPassword() == null) {
            throw new PasswordEmptyException("Campo de Contraseña nulo");
        } else if (userDto.getPassword().isEmpty()) {
            throw new PasswordEmptyException("Campo de Contraseña vacio");
        }
    }
}
