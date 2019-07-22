package com.example.oauth2server.config;

import java.util.stream.Stream;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    private static final String[] PATHS = {
        "/oauth/authorize",
        "/oauth/check_token",
        "/oauth/confirm_access",
        "/oauth/error",
        "/oauth/token",
        "/oauth/token_key",
    };

    private static final String PREFIX = "/v1";

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        Stream.of(PATHS).forEach(path -> endpoints.pathMapping(path, PREFIX + path));

        endpoints
            .tokenStore(tokenStore())
            .reuseRefreshTokens(false)
            .authorizationCodeServices(jdbcAuthorizationCodeServices());
    }
}
