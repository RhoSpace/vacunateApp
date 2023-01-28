package cl.vacunateapp.apigateway.service.authentication;

import cl.vacunateapp.apigateway.dto.UserDto;
import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.security.UserPrincipal;
import cl.vacunateapp.apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDto signInAndReturnJWT(UserDto signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getRut(), signInRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String tokenJwt = jwtProvider.generateToken(userPrincipal);

        User sigInUser = userPrincipal.getUser();
        sigInUser.setToken(tokenJwt);

        return UserDto.builder()
                .rut(sigInUser.getRut())
                .name(sigInUser.getName())
                .lastName(sigInUser.getLastName())
                .role(sigInUser.getRole())
                .token(sigInUser.getToken())
                .build();
    }
}
