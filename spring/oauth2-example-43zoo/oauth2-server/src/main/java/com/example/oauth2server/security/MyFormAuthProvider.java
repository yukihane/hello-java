package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class MyFormAuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            final UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            final String name = auth.getName();

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
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
