package com.example.customloginpage.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class BasicAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final MyAuthentication ret = new MyAuthentication(new MyPrincipal(name), new MyCredentials());
        ret.addAuthority(Authority.GOODBY);
        return ret;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return true;
    }

}
