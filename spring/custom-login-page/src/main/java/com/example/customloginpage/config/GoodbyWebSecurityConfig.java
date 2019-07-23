package com.example.customloginpage.config;

import com.example.customloginpage.security.BasicAuthenticationProvider;
import java.util.Arrays;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Order(101)
public class GoodbyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
            .requestMatchers()
            .antMatchers("/goodby/**")
            .and()
            .httpBasic()
            .and()
            .addFilter(
                new BasicAuthenticationFilter(new ProviderManager(Arrays.asList(new BasicAuthenticationProvider()))))
            .authorizeRequests()
            .antMatchers("/goodby/**").hasAnyAuthority("GOODBY", "HELLO");
    }
}
