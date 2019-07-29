package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@RequiredArgsConstructor
public class MyHeaderAuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            final PreAuthenticatedAuthenticationToken authen = (PreAuthenticatedAuthenticationToken) authentication;
            final String name = ((MyCredentials) authen.getCredentials()).getName();
            if (name == null) {
                throw new BadCredentialsException("Name is null");
            }
            userRepository.findByName(name)
                .orElseThrow(() -> new BadCredentialsException("Name is not found"));

            return new MyAuthentication(new MyCredentials(name), new MyPrincipal(name));

        } else {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.equals(authentication);
    }

}
