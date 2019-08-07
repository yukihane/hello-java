package com.example.oauth2client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class ClientConfiguration {

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(final OAuth2ClientContext context,
        final OAuth2ProtectedResourceDetails details) {
        // expireの確認をしたい場合はこのクラス生成部分を差し替える
        return new OAuth2RestTemplate(details, context);
    }

    /**
     * expireしたaccess-tokenでリクエストした場合の挙動が確認したかったので
     * オリジナルからexpiredかどうかの判別処理を取り除いている
     */
    public static class MyOAuth2RestTemplate extends OAuth2RestTemplate {

        private final OAuth2ClientContext context;

        public MyOAuth2RestTemplate(final OAuth2ProtectedResourceDetails resource, final OAuth2ClientContext context) {
            super(resource, context);
            this.context = context;
        }

        @Override
        public OAuth2AccessToken getAccessToken() throws UserRedirectRequiredException {

            OAuth2AccessToken accessToken = context.getAccessToken();

            //          if (accessToken == null || accessToken.isExpired()) {
            if (accessToken == null) {
                try {
                    accessToken = acquireAccessToken(context);
                } catch (final UserRedirectRequiredException e) {
                    context.setAccessToken(null); // No point hanging onto it now
                    accessToken = null;
                    final String stateKey = e.getStateKey();
                    if (stateKey != null) {
                        Object stateToPreserve = e.getStateToPreserve();
                        if (stateToPreserve == null) {
                            stateToPreserve = "NONE";
                        }
                        context.setPreservedState(stateKey, stateToPreserve);
                    }
                    throw e;
                }
            }
            return accessToken;
        }
    }

}
