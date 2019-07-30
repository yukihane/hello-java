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
        final String name = getName(request);
        return new MyPrincipal(name);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        final String name = getName(request);
        return new MyCredentials(name);
    }

    private static String getName(final HttpServletRequest request) {
        //        final String name = request.getHeader(HEADER_NAME);
        //  "suzuki" で認証された想定
        final String name = "suzuki";
        return name;
    }

}
