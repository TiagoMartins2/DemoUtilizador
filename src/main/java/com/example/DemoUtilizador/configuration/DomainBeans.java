package com.example.DemoUtilizador.configuration;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
public class DomainBeans {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String url;

    @Value("${keycloak.clientId}")
    private String clientId;

    @Value("${keycloak.secret}")
    private String secret;

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder().grantType(CLIENT_CREDENTIALS).serverUrl(url).realm(realm).clientId(clientId).clientSecret(secret).build();
    }
}
