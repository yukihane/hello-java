package com.example.oauth2server.config;

import com.example.oauth2server.repository.UserRepository;
import com.example.oauth2server.security.MyBasicAuthFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@Order(-1)
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().disable();
        http.headers().frameOptions().disable();
        http
            .requestMatcher(new NotOAuthResourceRequestMatcher())
            .authorizeRequests()
            .antMatchers("/error").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .addFilter(new MyBasicAuthFilter(userRepository));
    }

    private static class NotOAuthResourceRequestMatcher implements RequestMatcher {

        @Override
        public boolean matches(final HttpServletRequest request) {

            final String requestPath = getRequestPath(request);
            return !requestPath.startsWith("/v1")
                && !requestPath.startsWith("/api")
                && !requestPath.startsWith("/h2-console");
        }

        private String getRequestPath(final HttpServletRequest request) {
            String url = request.getServletPath();

            if (request.getPathInfo() != null) {
                url += request.getPathInfo();
            }

            return url;
        }

    }
}
