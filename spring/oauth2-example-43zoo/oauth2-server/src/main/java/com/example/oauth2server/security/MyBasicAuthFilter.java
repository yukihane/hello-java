package com.example.oauth2server.security;

import java.util.Arrays;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class MyBasicAuthFilter extends BasicAuthenticationFilter {

    public MyBasicAuthFilter(final MyAuthenticationProvider provider) {
        super(new ProviderManager(Arrays.asList(provider)));
    }

}
