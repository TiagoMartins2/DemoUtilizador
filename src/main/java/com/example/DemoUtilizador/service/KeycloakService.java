package com.example.DemoUtilizador.service;

import com.example.DemoUtilizador.model.Utilizador;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Slf4j
@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    public String realm;

    @Autowired
    private Keycloak keycloak;

    //@Autowired
    //private final DomainBeans domainB;

    public KeycloakService(Keycloak keycloak){
        this.keycloak = keycloak;
    }

    public Response addUser(Utilizador utilizador){
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(utilizador.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(utilizador.getUsername());
        user.setFirstName(utilizador.getFirstname());
        user.setLastName(utilizador.getLastname());
        user.setCredentials(Collections.singletonList(credentialRepresentation));
        user.setEmail(utilizador.getEmail());
        user.setEnabled(true);
        //user.setEmailVerified(false);
        //user.setRealmRoles(Arrays.asList("admin"));


        try(Response response = this.keycloak.realm(realm).users().create(user)){
            System.out.println(response.toString());
            return response;
        }
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
