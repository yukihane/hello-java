package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import java.util.Arrays;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class MyBasicAuthFilter extends BasicAuthenticationFilter {

    public MyBasicAuthFilter(final UserRepository userRepository) {
        super(new ProviderManager(Arrays.asList(new MyBasicAuthProvider(userRepository))));
    }

}
