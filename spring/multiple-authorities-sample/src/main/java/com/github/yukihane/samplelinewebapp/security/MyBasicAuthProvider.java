package com.github.yukihane.samplelinewebapp.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyBasicAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        if (name != null && name.startsWith("ok")) {
            final MyCredentials credentials = new MyCredentials();
            final MyPrincipal principal = new MyPrincipal(name);
            return new MyAuthentication(credentials, principal);
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
