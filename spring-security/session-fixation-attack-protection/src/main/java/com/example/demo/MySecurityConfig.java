package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class MySecurityConfig {

    @Order(100)
    @Configuration
    public static class MySecurityConfig0 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/insecure")
                .authorizeRequests(auth -> {
                    auth.anyRequest().permitAll();
                });
        }
    }

    @Order(101)
    @Configuration
    public static class MySecurityConfig1 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                .authorizeRequests(auth -> {
                    auth.antMatchers("/secure/**").authenticated();
                })
                .formLogin()
                .and()
                .sessionManagement(sess -> {
                    sess.sessionFixation(fix -> {
                        fix.changeSessionId();
                    });
                });
        }
    }
}