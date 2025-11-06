package com.example.EdufyCreator.converters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//ED-169-AWS
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${edufy.creator.client.id}")
    private String creatorClientId;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities =
                Stream.concat(
                        scopesConverter.convert(jwt).stream(),
                        extractClientRoles(jwt).stream()
                )
                        .collect(Collectors.toSet());
                return new JwtAuthenticationToken(jwt, authorities, principalName(jwt));
    }

    private String principalName(Jwt jwt) {
        String preferredUsername = jwt.getClaimAsString("preferred_username");
        if(preferredUsername != null && !preferredUsername.isBlank()) return preferredUsername;

        return jwt.getSubject();
    }


    private Collection<? extends GrantedAuthority> extractClientRoles(Jwt jwt) {
        if (!jwt.hasClaim("resource_access")) return Set.of();

        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");

        Object clientData = resourceAccess.get(creatorClientId);
        if(!(clientData instanceof Map<?, ?> clientMap)){
            return Set.of();
        }

        Object rolesData = clientMap.get("roles");
        if(!(rolesData instanceof Collection<?> roles)) {
            return Set.of();
        }

        return roles.stream()
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(role -> !role.isBlank())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toUnmodifiableSet());

    }

}
