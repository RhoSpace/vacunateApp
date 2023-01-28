package cl.vacunateapp.microservice_vaccinator.utils;

import cl.vacunateapp.microservice_vaccinator.dto.UserDto;
import cl.vacunateapp.microservice_vaccinator.exception.badrequest.lastname.LastNameEmptyException;
import cl.vacunateapp.microservice_vaccinator.exception.badrequest.name.NameEmptyException;
import cl.vacunateapp.microservice_vaccinator.exception.badrequest.password.PasswordEmptyException;
import cl.vacunateapp.microservice_vaccinator.exception.badrequest.rut.RutEmptyException;

public class DtoUtils {

    public static void checkDto(UserDto userDto) {
        checkEmptyNullRut(userDto.getRut());
        checkEmptyNullName(userDto.getName());
        checkEmptyNullLastName(userDto.getLastName());
        checkEmptyNullPassword(userDto.getPassword());
    }
    public static void checkEmptyNullRut(String rut) {
        //Compruebo si el rut existe en el dto
        if (rut == null) {
            throw new RutEmptyException("Campo de rut nulo");
        } else if (rut.isEmpty()) {
            throw new RutEmptyException("Campo de Rut vacio");
        }
    }

    public static void checkEmptyNullName(String name) {
        //Compruebo si el nombre existe en el dto
        if (name == null) {
            throw new NameEmptyException("Campo de Nombre nulo");
        } else if (name.isEmpty()) {
            throw new NameEmptyException("Campo de Nombre vacio");
        }
    }

    public static void checkEmptyNullLastName(String lastName) {
        //Compruebo si el apellido existe en el dto
        if (lastName == null) {
            throw new LastNameEmptyException("Campo de Apellido nulo");
        } else if (lastName.isEmpty()) {
            throw new LastNameEmptyException("Campo de Apellido vacio");
        }
    }

    public static void checkEmptyNullPassword(String password) {
        //Compruebo si la contraseña existe en el dto
        if (password == null) {
            throw new PasswordEmptyException("Campo de Contraseña nulo");
        } else if (password.isEmpty()) {
            throw new PasswordEmptyException("Campo de Contraseña vacio");
        }
    }
}
