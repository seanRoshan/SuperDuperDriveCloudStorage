package com.seanroshan.superduperdrivecloudstorage.services;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final HashService hashService;

    public AuthenticationService(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
