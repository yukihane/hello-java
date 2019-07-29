package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class MyHeaderAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final String HEADER_NAME = "X-LOGIN-NAME";

    public MyHeaderAuthFilter(final UserRepository repository) {
        final MyHeaderAuthProvider filter = new MyHeaderAuthProvider(repository);
        final AuthenticationManager authManager = new ProviderManager(Arrays.asList(filter));
        setAuthenticationManager(authManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        final String name = request.getHeader(HEADER_NAME);
        return new MyPrincipal(name);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        final String name = request.getHeader(HEADER_NAME);
        return new MyCredentials(name);
    }

}
