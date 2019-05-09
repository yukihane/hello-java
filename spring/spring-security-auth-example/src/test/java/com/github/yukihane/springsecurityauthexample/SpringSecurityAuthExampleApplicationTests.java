package com.github.yukihane.springsecurityauthexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringSecurityAuthExampleApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        final HttpEntity<String> entity = new HttpEntity<>("");
        final URI url = new URL("http://localhost:" + port + "/hello").toURI();
        final ResponseEntity<String> ret = this.restTemplate.exchange(url, HttpMethod.GET, entity,
            String.class);
        assertThat(ret.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
