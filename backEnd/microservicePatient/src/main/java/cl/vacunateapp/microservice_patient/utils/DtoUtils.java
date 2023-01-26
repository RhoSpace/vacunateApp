package cl.vacunateapp.microservice_patient.utils;

import cl.vacunateapp.microservice_patient.dto.PatientDto;
import cl.vacunateapp.microservice_patient.exception.badrequest.lastname.LastNameEmptyException;
import cl.vacunateapp.microservice_patient.exception.badrequest.name.NameEmptyException;
import cl.vacunateapp.microservice_patient.exception.badrequest.rut.RutEmptyException;

public class DtoUtils {

    public static void checkDto(PatientDto userDto) {
        checkEmptyNullRut(userDto.getRut());
        checkEmptyNullName(userDto.getName());
        checkEmptyNullLastName(userDto.getLastName());
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
}
