package com.example.oauth2server.config;

import com.example.oauth2server.repository.UserRepository;
import com.example.oauth2server.security.MyFormAuthFilter;
import com.example.oauth2server.security.MyHeaderAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class OAuthEndPointSecurityConfig {

    @Configuration
    @Order(1)
    @RequiredArgsConstructor
    public static class HeaderAuthConfig extends WebSecurityConfigurerAdapter {

        private final UserRepository userRepository;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/v1/authenticate/signin")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(new MyHeaderAuthFilter(userRepository));
        }
    }

    @Configuration
    @Order(2)
    @RequiredArgsConstructor
    public static class FormAuthConfig extends WebSecurityConfigurerAdapter {

        private final UserRepository userRepository;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/v1/**")
                .csrf().disable()
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //                .and()
                .formLogin()
                .loginPage("/v1/authenticate/identity")
                .loginProcessingUrl("/v1/authenticate/confirm")
                .and()
                .addFilter(new MyFormAuthFilter(userRepository))
                .authorizeRequests()
                .antMatchers("/v1/authenticate/identity").permitAll()
                .antMatchers("/v1/oauth/error").permitAll()
                .antMatchers("/v1/oauth/authorize").authenticated()
                .anyRequest().denyAll();
        }
    }
}
