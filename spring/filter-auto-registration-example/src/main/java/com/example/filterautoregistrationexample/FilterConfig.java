package com.example.filterautoregistrationexample;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filterautoregistrationexample.auth.MyHeaderAuthenticationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyHeaderAuthenticationFilter> registration(
        final MyHeaderAuthenticationFilter filter) {
        final FilterRegistrationBean<MyHeaderAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
