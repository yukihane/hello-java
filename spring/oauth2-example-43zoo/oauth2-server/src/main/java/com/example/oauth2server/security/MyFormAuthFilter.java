package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class MyFormAuthFilter extends UsernamePasswordAuthenticationFilter {

    public MyFormAuthFilter(final UserRepository userRepository) {
        super();
        setUsernameParameter("name");
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/v1/authenticate/confirm", "POST"));
        setAuthenticationManager(new ProviderManager(Arrays.asList(new MyFormAuthProvider(userRepository))));
        setAllowSessionCreation(false);
        setAuthenticationSuccessHandler(new MyForwardAuthenticationSuccessHandler());
    }

    public static class MyForwardAuthenticationSuccessHandler extends ForwardAuthenticationSuccessHandler {

        public MyForwardAuthenticationSuccessHandler() {
            super("/v1/oauth/authorize");
        }

        @Override
        public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {

            final HttpServletRequestWrapper req = new HttpServletRequestWrapper(request) {
                @Override
                public java.security.Principal getUserPrincipal() {
                    return authentication;
                }
            };
            super.onAuthenticationSuccess(req, response, authentication);

        }
    }
}
