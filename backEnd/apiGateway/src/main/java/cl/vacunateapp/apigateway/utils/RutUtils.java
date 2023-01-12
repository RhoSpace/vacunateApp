package cl.vacunateapp.apigateway.utils;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.exception.badrequest.rut.BadRutException;
import cl.vacunateapp.apigateway.exception.badrequest.rut.RutRegisteredException;
import cl.vacunateapp.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RutUtils {


    @Autowired
    static UserRepository userRepository;

    public static void checkValidRut(UserDto userDto) {

        boolean validacion = false;
        String rutVerficacion = userDto.getRut();
        try {
            rutVerficacion = rutVerficacion.toUpperCase();
            rutVerficacion = rutVerficacion.replace(".", "");
            rutVerficacion = rutVerficacion.replace("-", "");
            int rutAux = Integer.parseInt(rutVerficacion.substring(0, rutVerficacion.length() - 1));

            char dv = rutVerficacion.charAt(rutVerficacion.length() - 1);

            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (Exception e) {
            throw new BadRutException("El rut: " + userDto.getRut() + ", no es valido");
        }
    }
}
