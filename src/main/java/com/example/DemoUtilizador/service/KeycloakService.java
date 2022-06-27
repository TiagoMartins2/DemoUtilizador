package com.example.DemoUtilizador.service;

import com.example.DemoUtilizador.configuration.DomainBeans;
import com.example.DemoUtilizador.model.Utilizador;
import lombok.AllArgsConstructor;
import org.h2.engine.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {

    @Value("${keycloak.realm}")
    public String realm;

    private final DomainBeans domainB;

    public KeycloakService(DomainBeans domainBeans){
        this.domainB = domainBeans;
    }

    public void addUser(Utilizador utilizador){
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

        UsersResource instance = getInstance();
        instance.create(user);
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public UsersResource getInstance(){
        return domainB.getInstance().realm(domainB.realm).users();
    }



}
