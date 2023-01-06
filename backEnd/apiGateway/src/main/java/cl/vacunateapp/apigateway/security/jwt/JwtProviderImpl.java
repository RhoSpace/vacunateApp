package cl.vacunateapp.apigateway.security.jwt;

import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProvider {
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserPrincipal userPrincipal) {
        String authorities = userPrincipal.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", authorities)
                .claim("userId",userPrincipal.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // Extrae todos los valores del token
    private Claims extractClaims(HttpServletRequest request) {
        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if (token == null) {
            return null;
        }

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtiener autenticacion
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        Claims claims = extractClaims(request);

        if (claims == null) {
            return null;
        }

        String rut = claims.getSubject();
        Long userId = claims.get("userId", Long.class);
        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails details = UserPrincipal.builder()
                .rut(rut)
                .authorities(authorities)
                .id(userId)
                .build();

        if (rut == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(details, null, authorities);
    }

    // Comprueba si el token es valido
    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        Claims claims = extractClaims(request);

        if (claims == null) {
            return false;
        } else if (claims.getExpiration().before(new Date())) {
            return false;
        }

        return true;
    }
}
