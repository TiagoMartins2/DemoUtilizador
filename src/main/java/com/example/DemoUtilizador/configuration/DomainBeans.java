package com.example.DemoUtilizador.configuration;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;


@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class}) //Para desativar a configuração automática
@Configuration
public class DomainBeans {

    @Value("${keycloak.realm}")
    public String realm;

    @Value("${keycloak.auth-server-url}")
    public String url;

    @Value("${keycloak.clientId}")
    public String clientId;

    @Value("${keycloak.secret}")
    public String secret;

    private static Keycloak keycloak = null;

    public DomainBeans(){

    }

    @Bean
    public Keycloak getInstance(){
        if(keycloak == null){
            return KeycloakBuilder.builder()
                    .grantType(CLIENT_CREDENTIALS)
                    .serverUrl(url)
                    .realm(realm)
                    .clientId(clientId)
                    .clientSecret(secret)
                    .resteasyClient(
                            new ResteasyClientBuilder()
                                    .connectionPoolSize(10).build()
                    )
                    .build();
        }
        return keycloak;
    }

}
