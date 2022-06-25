package com.example.DemoUtilizador.service;

import com.example.DemoUtilizador.configuration.DomainBeans;
import com.example.DemoUtilizador.model.Utilizador;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    public String realm;

    private final DomainBeans domainB;

    public KeycloakService(DomainBeans domainBeans){
        this.domainB = domainBeans;
    }

    public Response createKeycloakUser(Utilizador utilizador){
        UsersResource usersResource = domainB.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(utilizador.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(utilizador.getUsername());
        user.setCredentials(Collections.singletonList(credentialRepresentation));
        user.setFirstName(utilizador.getFirstname());
        user.setLastName(utilizador.getLasname());
        user.setEmail(utilizador.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(false);

        Response response = usersResource.create(user);
        return response;
    }


    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
