package com.example.EdufyCreator.configs;

import com.example.EdufyCreator.converters.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//ED-154-AA
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //add converter

    //ED-154-AA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthConverter jwtAuthConverter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(frameOptions -> frameOptions.disable()))
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/h2-console/**").permitAll() //ED-170-AA
                                .anyRequest().permitAll() //change later
                //ED-169-AWS connected auth converter
                )
                   .oauth2ResourceServer(oauth2 ->
                         oauth2
                                 .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                 );

        return http.build();
    }
}
