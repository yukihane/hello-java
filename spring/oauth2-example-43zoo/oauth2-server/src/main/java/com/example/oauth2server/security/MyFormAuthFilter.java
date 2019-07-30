package com.example.oauth2server.security;

import com.example.oauth2server.repository.UserRepository;
import java.util.Arrays;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class MyFormAuthFilter extends UsernamePasswordAuthenticationFilter {

    public MyFormAuthFilter(final UserRepository userRepository) {
        super();
        setUsernameParameter("name");
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/v1/authenticate/confirm", "POST"));
        setAuthenticationManager(new ProviderManager(Arrays.asList(new MyFormAuthProvider(userRepository))));
    }

}
