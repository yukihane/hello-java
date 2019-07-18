package com.example.filterautoregistrationexample;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.filterautoregistrationexample.auth.MyHeaderAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyHeaderAuthenticationFilter filter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/api/private/**")
            .authorizeRequests().anyRequest()
            .authenticated();
        http.addFilter(filter);
    }
}
