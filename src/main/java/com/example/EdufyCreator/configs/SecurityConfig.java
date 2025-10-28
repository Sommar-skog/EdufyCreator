package com.example.EdufyCreator.configs;

import jakarta.persistence.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(frameOptions -> frameOptions.disable()))
                .authorizeHttpRequests(auth ->
                        auth
                                .anyRequest().permitAll() //change later
                                .requestMatchers("/h2-console/**").permitAll()
                );
                  /* .oauth2ResourceServer(oauth2 ->
                         oauth2
                                 .jwt(jwt -> jwt.jwtAuthenticationConverter())
                 );*/
        return http.build();
    }
}
