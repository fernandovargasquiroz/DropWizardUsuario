/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbdd.usuario.rest.dao;



import java.security.Principal;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


/**
 *
 * @author osboxes
 */
@Singleton
public class TrivialAuthenticator implements Authenticator<BasicCredentials, Principal> {
    private final static Principal INSTANCE = () -> "Local Authenticated user";
    private final String password;

    @Inject
    public TrivialAuthenticator() {
        password = "postgres";
    }

    @Override
    public Optional<Principal> authenticate(BasicCredentials credentials) throws AuthenticationException {
        return credentials.getPassword().equals(password) ? Optional.of(INSTANCE) : Optional.empty();
    }
}