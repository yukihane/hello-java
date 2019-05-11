package com.github.yukihane.springsecurityauthexample;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringSecurityAuthExampleApplicationTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;

    @DisplayName("未認証の場合401になる")
    @Test
    public void unauthrized() throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        final List<String> authHeader = new ArrayList<>();
        authHeader.add("Basic bXluYW1lOmVl");
        headers.put("Authorization", authHeader);
        final HttpEntity<String> entity = new HttpEntity<>("", headers);

        final URI url = new URL("http://localhost:" + port + "/hello").toURI();
        final ResponseEntity<String> ret = this.restTemplate.exchange(url, HttpMethod.GET, entity,
            String.class);
        assertThat(ret.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @DisplayName("認証OK")
    @Test
    public void authorized() throws MalformedURLException, URISyntaxException {
        final HttpEntity<String> entity = new HttpEntity<>("");
        final URI url = new URL("http://localhost:" + port + "/hello").toURI();
        final ResponseEntity<String> ret = this.restTemplate.exchange(url, HttpMethod.GET, entity,
            String.class);
        assertThat(ret.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
