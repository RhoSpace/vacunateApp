package cl.vacunateapp.apigateway.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;


@Log4j2
public class SecurityUtils {

    //Prefijo para los roles
    public static final String ROLE_PREFIX = "ROLE_";

    //Variables necesarias para el token
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_HEADER_TYPE = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_HEADER_TYPE + " ";


    //Metodo para convertir el rol en un rol valido para springSecurity
    public static SimpleGrantedAuthority convertToAuthority(String role) {
        RoleUtils.checkRole(role);
        String formattedRole = "";
        formattedRole = (role.startsWith(ROLE_PREFIX)) ? role : ROLE_PREFIX + role;

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
