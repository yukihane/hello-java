package com.example.oauth2server.security;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.oauth2server.entity.User;
import com.example.oauth2server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        // この辺りは認証フィルタ(今回の場合Basic認証を指定しているので
        // BasicAuthenticationFilter)が生成している
        final UsernamePasswordAuthenticationToken upAuth = (UsernamePasswordAuthenticationToken) authentication;
        final String username = (String) authentication.getPrincipal();

        try {
            final String password = (String) upAuth.getCredentials();

            final String storedPassword = queryPassword(username);

            if (Objects.equals(password, "") || !Objects.equals(password, storedPassword)) {
                throw new BadCredentialsException("illegal id or passowrd");
            }
        } catch (final SQLException e) {
            throw new AuthenticationServiceException("db error", e);
        }

        final Object principal = authentication.getPrincipal();
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
            principal, authentication.getCredentials(),
            Collections.emptyList());
        result.setDetails(authentication.getDetails());

        return result;
    }

    private String queryPassword(final String username) throws SQLException {
        return userRepository.findByName(username).map(User::getPassword)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return true;
    }

}