package cl.vacunateapp.apigateway.security.jwt;

import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrincipal userPrincipal);

    String generateToken(User user);

    // Obtiener autenticacion
    Authentication getAuthentication(HttpServletRequest request);

    // Comprueba si el token es valido
    boolean isTokenValid(HttpServletRequest request);
}
