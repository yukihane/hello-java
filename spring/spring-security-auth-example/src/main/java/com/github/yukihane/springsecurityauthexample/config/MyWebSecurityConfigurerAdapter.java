package com.github.yukihane.springsecurityauthexample.config;

import com.github.yukihane.springsecurityauthexample.security.MyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationProvider authProvider;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        log.debug(
            "Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        http.authenticationProvider(authProvider);
    }
}
