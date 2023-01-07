package cl.vacunateapp.apigateway.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class SecurityUtils {

    //Prefijo para los roles
    public static final String ROLE_PREFIX = "ROLE_";

    //Variables necesarias para el token
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_HEADER_TYPE = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_HEADER_TYPE + " ";


    //Metodo para convertir el rol en un rol valido para springSecurity
    public static SimpleGrantedAuthority convertToAuthority(String role) {

        String formattedRole = "";

        if (role.startsWith(ROLE_PREFIX)) {
            formattedRole = role;
        } else {
            formattedRole = ROLE_PREFIX + role;
        }

        return new SimpleGrantedAuthority(formattedRole);
    }

    //Extraer token
    public static String extractAuthTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
