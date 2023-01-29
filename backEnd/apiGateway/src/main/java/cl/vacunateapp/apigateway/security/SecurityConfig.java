package cl.vacunateapp.apigateway.security;

import cl.vacunateapp.apigateway.entity.Role;
import cl.vacunateapp.apigateway.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = managerBuilder.build();

        return http.cors()
                .and()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/authentication/sign-up", "/api/authentication/sign-in")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/gateway/vaccinator/list", "/gateway/vaccinator/count/user").permitAll()
                .requestMatchers("/gateway/vaccinator/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/gateway/patient/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers("/gateway/vaccine/update_count_when_patient_vaccinated/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers("/gateway/vaccine//update_count/**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .authorizeHttpRequests()
                .and()
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
