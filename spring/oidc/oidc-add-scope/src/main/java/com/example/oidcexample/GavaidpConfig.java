package com.example.oidcexample;

import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.oidc.authentication.OidcIdTokenDecoderFactory;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;

@Profile("gavaidp")
@Configuration
public class GavaidpConfig {

    @Bean
    public JwtDecoderFactory<ClientRegistration> idTokenDecoderFactory() {
        final OidcIdTokenDecoderFactory idTokenDecoderFactory = new OidcIdTokenDecoderFactory();
        idTokenDecoderFactory.setJwsAlgorithmResolver(clientRegistration -> {
            return MacAlgorithm.HS256;
        });
        return idTokenDecoderFactory;
    }

    @Bean
    OidcUserService oidcUserService() {
        final OidcUserService ret = new OidcUserService();
        ret.setAccessibleScopes(Set.of());
        return ret;
    }
}
