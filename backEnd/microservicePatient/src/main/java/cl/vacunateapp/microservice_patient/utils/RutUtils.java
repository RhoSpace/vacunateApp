package cl.vacunateapp.microservice_patient.utils;

import cl.vacunateapp.microservice_patient.exception.badrequest.rut.RutInvalidException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RutUtils {
    public static String checkValidRut(String rut) {
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }

            if (!(dv == (char) (s != 0 ? s + 47 : 75))) {
                throw new RutInvalidException(rut);
            }
            return rut;
        }catch (Exception e) {
            throw new RutInvalidException(rut);
        }
    }
}
