package com.example.springbootauthexample202006.security;

import com.example.springbootauthexample202006.user.ApplicationUser;
import com.example.springbootauthexample202006.user.ApplicationUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ApplicationUserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        final String username = (String) auth.getPrincipal();
        final String password = (String) auth.getCredentials();

        final Optional<ApplicationUser> user = applicationUserRepository.findByUsername(username);

        final Optional<ApplicationUserAuthentication> result = user.map(u -> {
            if (passwordEncoder.matches(password, u.getPassword())) {
                return new ApplicationUserAuthentication(username);
            } else {
                return null;
            }
        });

        return result.orElseThrow(() -> new BadCredentialsException("illegal username or password"));
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
