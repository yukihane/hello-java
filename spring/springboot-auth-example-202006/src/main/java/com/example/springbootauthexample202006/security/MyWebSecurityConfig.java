package com.example.springbootauthexample202006.security;

import com.example.springbootauthexample202006.user.ApplicationUserRepository;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users/sign-up")
            .permitAll()
            .anyRequest().authenticated();

        final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final AuthenticationProvider provider = new ApplicationUserAuthenticationProvider(passwordEncoder,
            applicationUserRepository);
        final AuthenticationManager manager = new ProviderManager(Arrays.asList(provider));

        http.addFilter(new JWTAuthenticationFilter(manager));
        http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);
    }
}
