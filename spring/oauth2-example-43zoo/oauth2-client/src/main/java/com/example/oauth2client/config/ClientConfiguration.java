package com.example.oauth2client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class ClientConfiguration {

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(final OAuth2ClientContext context,
        final OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, context);
    }

}