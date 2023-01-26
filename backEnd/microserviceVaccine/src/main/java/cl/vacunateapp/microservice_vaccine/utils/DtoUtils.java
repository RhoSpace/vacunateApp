package cl.vacunateapp.microservice_vaccine.utils;

import cl.vacunateapp.microservice_vaccine.dto.VaccineDto;
import cl.vacunateapp.microservice_vaccine.exception.badrequest.name.NameEmptyException;

public class DtoUtils {

    public static void checkDto(VaccineDto vaccineDto) {
        checkEmptyNullName(vaccineDto.getName());
    }

    public static void checkEmptyNullName(String name) {
        //Compruebo si el nombre existe en el dto
        if (name == null) {
            throw new NameEmptyException("Campo de Nombre nulo");
        } else if (name.isEmpty()) {
            throw new NameEmptyException("Campo de Nombre vacio");
        }
    }
}
