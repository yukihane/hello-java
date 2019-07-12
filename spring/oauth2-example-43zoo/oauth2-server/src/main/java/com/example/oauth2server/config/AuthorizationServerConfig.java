package com.example.oauth2server.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import lombok.RequiredArgsConstructor;

// https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/html5/#registering-a-redirect-uri-with-the-client
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

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
            .authorizedGrantTypes("authorization_code", "refresh_token")
            .redirectUris("http://localhost:8081/client/tasks");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore())
            .reuseRefreshTokens(false)
            .authorizationCodeServices(jdbcAuthorizationCodeServices());
    }
}
