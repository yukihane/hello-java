package com.github.yukihane.java.beanvalidationrest.controller;

import static org.junit.Assert.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yukihane.java.beanvalidationrest.controller.response.ValidationResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    private URL url;

    @Before
    public void setUp() throws Exception {
        this.url = new URL("http://localhost:" + port + "/room");
    }

    private String getJson(final String name) throws IOException {
        final InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if (is == null) {
            throw new FileNotFoundException(name);
        }
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }

    @Test
    public void getHello() throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final String body = getJson("json/testdata/classroom01.json");
        final HttpEntity<String> entity = new HttpEntity<>(body, headers);

        final ResponseEntity<ValidationResponse> response = template.postForEntity(url.toURI(), entity,
            ValidationResponse.class);

        final ValidationResponse expected = objectMapper.readValue(getJson("json/expected-result/result01.json"),
            ValidationResponse.class);

        //        assertEquals(expected, response.getBody());
        final ValidationResponse result = response.getBody();
        assertEquals(1, result.getErrors().size());

    }

}
