package com.example.customloginpage.config;

import com.example.customloginpage.security.HelloAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .requestMatchers()
            .antMatchers("/hello/**")
            .and()
            .authorizeRequests()
            .antMatchers("/hello/**").hasAuthority("HELLO");

        http
            .requestMatchers()
            .antMatchers("/login/**")
            .and()
            .authenticationProvider(new HelloAuthenticationProvider())
            .formLogin()
            .loginPage("/login/hello")
            .loginProcessingUrl("/login/hello_processing")
            .permitAll()
            .and()
            .authenticationProvider(new HelloAuthenticationProvider());
    }

}
