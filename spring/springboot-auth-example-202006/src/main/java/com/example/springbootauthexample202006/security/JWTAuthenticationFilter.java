package com.example.springbootauthexample202006.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.example.springbootauthexample202006.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.springbootauthexample202006.security.SecurityConstants.HEADER_STRING;
import static com.example.springbootauthexample202006.security.SecurityConstants.SECRET;
import static com.example.springbootauthexample202006.security.SecurityConstants.TOKEN_PREFIX;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMpper = new ObjectMapper();

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super();
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest req,
        final HttpServletResponse res) throws AuthenticationException {
        try {
            final LoginForm form = objectMpper.readValue(req.getInputStream(), LoginForm.class);

            final UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword());

            return getAuthenticationManager().authenticate(creds);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest req,
        final HttpServletResponse res,
        final FilterChain chain,
        final Authentication auth) throws IOException, ServletException {

        final String token = JWT.create()
            .withSubject(auth
                .getName())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
