package com.example.oauth2server.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.oauth2server.security.MyAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Order(2)
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationProvider authProvider;

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
            .authenticationProvider(authProvider);
    }

    private static class NotOAuthResourceRequestMatcher implements RequestMatcher {

        @Override
        public boolean matches(final HttpServletRequest request) {

            final String requestPath = getRequestPath(request);
            return !requestPath.startsWith("/api") && !requestPath.startsWith("/h2-console");
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
