package com.github.yukihane.samplelinewebapp.config;

import com.github.yukihane.samplelinewebapp.security.MyBasicAuthProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MyWebSecurityConfig {

    @Configuration
    @Order(1)
    public static class OAuthConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/line/**")
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2Login();
        }
    }

    @Configuration
    @Order(2)
    public static class OAuthClientConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/oauth2/**")
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2Client();
        }
    }

    @Configuration
    @Order(3)
    public static class OAuthClientAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/login/oauth2/code/**")
                //                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2Login();
        }
    }

    @Configuration
    @Order(5)
    public static class BasicAuthConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/**")
                .authorizeRequests()
                .anyRequest().hasAuthority("basicauth")
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(new MyBasicAuthProvider());
        }
    }

    @Configuration
    @Order(6)
    public static class AllDenyConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/**")
                .authorizeRequests()
                .anyRequest().denyAll();
        }
    }
}
