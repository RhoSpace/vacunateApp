package cl.vacunateapp.apigateway.security;

import cl.vacunateapp.apigateway.entity.User;
import cl.vacunateapp.apigateway.service.UserService;
import cl.vacunateapp.apigateway.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    //Metodo carga los datos del usuario en detalle
    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {

        User user = userService.findByRut(rut);

        //Rol asignado en el formato de spring Security
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        //Construccion del UserDetails
        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .rut(user.getRut())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
