package com.example.oidcexample;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class HelloControllerTest {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    /**
     * 未ログイン状態の場合、IdPへリダイレクトする。
     */
    @Test
    public void 未ログイン状態(@Autowired final MockMvc mvc) throws Exception {
        mvc.perform(get("/")).andExpect(status().is3xxRedirection())
            .andExpect(header().exists("Location"));
    }

    @Test
    public void ログイン状態(@Autowired final MockMvc mvc) throws Exception {

        // https://docs.spring.io/spring-security/site/docs/current/reference/html5/#testing-oauth2-client
        mvc.perform(
            get("/")
                .with(oidcLogin()
                    .clientRegistration((this.clientRegistrationRepository.findByRegistrationId("myspring")))

                ))
            .andExpect(status().isOk());
    }

    @TestConfiguration
    static class AuthorizedClientConfig {
        @Bean
        OAuth2AuthorizedClientRepository authorizedClientRepository() {
            return new HttpSessionOAuth2AuthorizedClientRepository();
        }
    }

}
