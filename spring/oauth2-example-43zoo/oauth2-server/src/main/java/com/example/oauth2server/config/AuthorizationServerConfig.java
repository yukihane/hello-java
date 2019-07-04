package com.example.oauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

// https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/html5/#registering-a-redirect-uri-with-the-client
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        final String secret = passwordEncoder().encode("noonewilleverguess");
        clients
            .inMemory()
            .withClient("first-client")
            .secret(secret)
            .scopes("read")
            .authorizedGrantTypes("authorization_code")
            .redirectUris("http://localhost:8081/client/tasks");
    }
}
