package com.github.yukihane.springsecurityauthexample.config;

import com.github.yukihane.springsecurityauthexample.security.MyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationProvider authProvider;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        http.authenticationProvider(authProvider);
    }
}
